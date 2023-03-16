package com.spi.serviceimpl;

import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * spi->services provider interface
 *在jdk6里面引进的一个新的特性ServiceLoader，从官方的文档来说，它主要是用来装载一系列的service provider
 * 其主要依赖的类是ServiceLoader，这个类可以通过service provider的配置文件来装载指定的service provider
 * 当服务的提供者，提供了服务接口的一种实现之后，我们只需要在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。
 * 该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块（jar包）的时候，
 * 就能通过该jar包（jar包本身也会分配一个classpath）或者外部程序（自定编写的项目）classpath下的META-INF/services/里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入
 */
public class TestSpi {
    public static void main(String[] args) {
        //创建指定接口的ServiceLoader<>,在创建时，load方法会在项目的classpath下去找/WETA-INF/services/com.sql.ServiceTest文件，
        // 文件需要配置实现类的全限定类名，所以这只是ServiceLoader类在加载实现类的机制
        //那么java定义出来的这种spi机制，会被运用到哪些情况下呢：
        /*
        1：比方说java在JDBC这一块，java定义接口Connection接口，而真正实现的接口是各大数据库厂商，
        在创建连接之前，我们一般会加载驱动类Driver，这个Driver定义在java.sql.Driver,同样由厂商实现，
        对于mysql数据库来说，我们在调用DriverManager.getConnection()之前，好像可以不加载驱动类，也能创建连接，那这是我i什么呢：
        这里就是靠ServiceLoader<>类了，在DriverManager类中有一个静态代码块：
        static {
            loadInitialDrivers();
            println("JDBC DriverManager initialized");
        }
        再看loadInitialDrivers()方法体：
        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        Iterator<Driver> drivers = loadedDrivers.iterator();
        println("DriverManager.initialize: jdbc.drivers = " + loadedDrivers);
        所以点开mysql的jar包META-INF/services/java.sql.Driver文件：com.mysql.cj.jdbc.Driver，就是mysql对于Driver的实现类
        所以我们在使用mysql数据库时，并不用去编写加载驱动类的代码，DriverManager在被加载时就已经做好了准备工作
        ！！！！！也要明白一点，这种spi机制要靠第三方实现并支持，也就是在jar包中注册spi文件。
        所以对于没有实现该规范的数据库，我们仍然需要手动架加载驱动类，比如oracle，class.formName(驱动类路径);
        2：在tomcat部署项目时，少不了的就是Servlet，而我们都知道，可以通过web.xml来配置Servlet，
        然而tomcat也可脱离配置文件，就是jakarta.servlet.ServletContainerInitializer这个类，
        tomcat会在启动时读取项目classpath路径下的META-INF/services/jakarta.servlet.ServletContainerInitializer这个spi注册文件
        如果这个文件中指明了项目中实现类路径，那么tomcat就会把部署Servlet的任务交给这个类来实完成，实例化该类并调用onStartUp方法
            =====详情见com.spi.tomcatInitServlet.TomcatInitServlet，可以发现，tomcat在启动时，实例化了这个类，并调用了onStartup()方法===
        3：那么在表现层整合sprignmvc框架时，springmvc为了摆脱基于web.xml配置，同样实现了该接口
        实现类：org.springframework.web.SpringServletContainerInitializer,结合注解@HandlesTypes（WebApplicationInitializer.class）
        导入springjar包之后，我们就可以配置实现了WebApplicationInitializer接口的类，来代替web.xml配置tomcat容器的ServletContext，
        也就是将我们的自定义Servlet添加到tomcat中
            ①：由tomcat在启动时根据SPI机制的ServiceLoader#load方法拿到所有JavaEE接口（ServletContainerInitializer）注册的实现类。
            ②：Spring对该接口的实现类是SpringServletContainerInitializer，其类上标注了@HandlesTypes({WebApplicationInitializer.class})。
            ③：tomcat从classpath下找到所有的WebApplicationInitializer实现类，将所有的实现类传入SpringServletContainerInitializer#onStartup方法的第一个参数，调用方法。
            ④：回到SpringServletContainerInitializer#onStartup方法中的逻辑，将所有的WebApplicationInitializer实现类的onStartup方法一一调用。
            ⑤：WebApplicationInitializer的实现类之一是AbstractDispatcherServletInitializer，会创建spring容器、配置web.xml、注册过滤器。
            =====详情见com.spi.tomcatInitServlet.SpringInitServlet=====
         */
        ServiceLoader<ServiceTest> serviceLoader=ServiceLoader.load(ServiceTest.class);
        Iterator<ServiceTest> interator=serviceLoader.iterator();
        while (interator.hasNext()) {
            ServiceTest service = interator.next();
            service.sayHello();
        }
    }
}
