package com.spi.tomcatInitServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
/**
 * Application Context 是 BeanFactory 的子接口，也被称为 Spring 上下文。
 * Application Context 是 spring 中较高级的容器。和 BeanFactory 类似，它可以加载配置文件中定义的 bean，
 * 将所有的 bean 集中在一起，当有请求的时候分配 bean。 另外，它增加了企业所需要的功能，比如，从属性文件中解析文本信息和将事件传递给所指定的监听器。
 * 这个容器在 org.springframework.context.ApplicationContext interface 接口中定义。
 *
 * ApplicationContext 包含 BeanFactory 所有的功能，一般情况下，相对于 BeanFactory，ApplicationContext 会更加优秀。
 * 当然，BeanFactory 仍可以在轻量级应用中使用，比如移动设备或者基于 applet 的应用程序。
 *
 * 最常被使用的 ApplicationContext 接口实现：
 *
 * 1:FileSystemXmlApplicationContext：该容器从 XML 文件中加载已被定义的 bean。在这里，你需要提供给构造器 XML 文件的完整路径。
 *
 * 2:ClassPathXmlApplicationContext：该容器从 XML 文件中加载已被定义的 bean。
 * 在这里，你不需要提供 XML 文件的完整路径，只需正确配置 CLASSPATH 环境变量即可，因为，容器会从 CLASSPATH 中搜索 bean 配置文件。
 *
 * 3:XmlWebApplicationContext：该容器会在一个 web 应用程序的范围内加载在 XML 文件中已被定义的 bean。
 */
public class SpringInitServlet implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext context=new XmlWebApplicationContext();
    }
}
