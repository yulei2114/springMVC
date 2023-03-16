package com.springbasedconfigclass;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * tomcat容器完全脱离web.xml，我们通过定义类来实现ServletContainerInitializer接口，tomcat在启动时会将配置ServletContext容器的任务
 * 交给这个类,
 * 首先我们要知道这是个Java EE规范中的接口，由具体的Servlet容器来实现，在Web容器启动时被回调，也就是说，我们可以通过这个接口来自定义ServletContext
 * 从而脱离配置文件
 * 在实现该接口后，需要在SPI文件中注册，在Servlet容器启动时通过SPI从classpath下查找其实现类，实例化后进行回调。
 */
/*
    这个类用来代替web.xml文件，所以如果配置了ServletContainerInitializer接口的实现类，就必须要注册spi文件并符合路径规范
    当同时配置web.xml和ServletContainerInitializer实现类之后
    这里我们不用自己去注册spi文件，利用spring框架的实现类SpringServletContainerInitializer,该类使用handleTypes注解注册了
    org.springframework.web.WebApplicationInitializer接口，所以我们只需要实现这个接口，
    当tomcat获取SpringServletContainerInitializer类时，
    会去classpath下找所有实现了WebApplicationInitializer接口的类，通过反射获取实现类的Class对象
    ！！！并挨个传入SpringServletContainerInitializer实现类的onStartUp()方法的第一个参数
    最后SpringServletContainerInitializer调用onStartUp()方法，方法源码逻辑如下：
        public void onStartup(@Nullable Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
                List<WebApplicationInitializer> initializers = Collections.emptyList();
                Iterator var4;
                if (webAppInitializerClasses != null) {
                initializers = new ArrayList(webAppInitializerClasses.size());
                var4 = webAppInitializerClasses.iterator();

                while(var4.hasNext()) {
                Class<?> waiClass = (Class)var4.next();
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) && WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
                    try {
                        ((List)initializers).add((WebApplicationInitializer)ReflectionUtils.accessibleConstructor(waiClass, new Class[0]).newInstance());
                    } catch (Throwable var7) {
                        throw new ServletException("Failed to instantiate WebApplicationInitializer class", var7);
                    }
                }
            }
        }
    那么在这个onStartUp()方法中，将传递进来的WebApplicationInitializer实现类挨个实例化
    并挨个调用WebApplicationInitializer实现类中的onStartUp(ServletContext servletContext)方法,
    这个方法里面将接受SpringServletContainerInitializer接收到的ServletContext对象，也就是tomcat传递过来的ServletContext
    所以，我们就可以通过直接继承spring提供的WebApplicationInitializer接口，并重写onStartUp方法，继而实现ServletContext配置
    而不是直接去实现tomcat提供的jakarta.servlet.ServletContainerInitializer接口，不用去注册spi文件
    完美！！！！！！！！！！！！！！！！！！！！！
 */
public class InitialTomcatContextConfigAndXml{

}
        /**
        implements WebApplicationInitializer {



    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //创建DispatcherServlet所需的spring容器
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        //设置配置文件的位置
        appContext.setConfigLocation("/WEB-INF/classes/springmvc.xml");
        //将DispatcherServlet实例添加到容器
        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
        //设置启动顺序
        dispatcher.setLoadOnStartup(1);
        //设置映射路径
        dispatcher.addMapping("/");
    }
        */
    //那么我们摆脱了通过配置文件配置Servlet(DiapatcherSerlet)，再一想，xmlWebApplicationContext容器依旧要依赖xml文件来配置
    //所以，既然配置Servlet摆脱了xml配置，再进一步，直接通过配置类来代替配置前端控制器DiapatcherServlet的内置容器
    //那么就可用AnnotationConfigWebApplicationContext这个容器,通过register(MyMvcConfig.class)，传入配置类，完成容器内注册
    /*Controller(Handler)处理器，HandlerMapping处理器映射器,HandlerAdapter处理器适配器,InternalResourceViewResolver视图解析器

     */
    /**
    public class MyWebAppInitializer implements WebApplicationInitializer {
        @Override
        public void onStartup(ServletContext container) {
            // 创建Spring的root配置环境
            AnnotationConfigWebApplicationContext rootContext =
                    new AnnotationConfigWebApplicationContext();
            rootContext.register(MyMvcConfig.class);
            // 将Spring的配置添加为listener
            container.addListener(new ContextLoaderListener(rootContext));
            // 创建SpringMVC的分发器
            AnnotationConfigWebApplicationContext dispatcherContext =
                    new AnnotationConfigWebApplicationContext();
            dispatcherContext.register(DispatcherConfig.class);
            // 注册请求分发器
            ServletRegistration.Dynamic dispatcher =
                    container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping("/");
        }
    }
    */
    //这是配置类，@EnableWebMvc声明处理器映射器和处理器设配器，@ComponentScan(),扫描指定包下的Controller,@Bean配置视图解析器
    /*
    @Configuration
    @EnableWebMvc
    @ComponentScan
    public class MyMvcConfig{
        @Bean
        public InternalResourceViewResolver viewResolver {
            InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
            viewResolver.setPrefix("/WEB-INF/classes/views");
            viewResolver.setSuffix(".jsp");
            return viewResolver;
        }
    }
     */

