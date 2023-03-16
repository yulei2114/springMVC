package com.springbasedxml.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
    JDK1.8的新特性：1：在接口中可以直接定义静态方法，而不需要实现类，可以直接通过接口名.方法名直接调用
                    2： 在接口中声明实例方法，相当于接口对其默认实现，实现类在继承时不需要实现，直接调用即可，但是必须以defaulet关键字修饰
                    3：public interface ServletRequestListener extends EventListener {
                        default void requestDestroyed(ServletRequestEvent sre) {
                            }

                        default void requestInitialized(ServletRequestEvent sre) {
                        }
                    }

 */
/**监听器的配置：
        监听对象	                           监听器接口	                                         监听事件
        ServletContext	ServletContextListener ServletContextAttributeListener	ServletContextEvent ServletContextAttributeEvent
        HttpSession	        HttpSessionListener HttpSessionActivationListener	HttpSessionEvent
        HttpSession	    HttpSessionAttributeListener HttpSessionBindingListener	HttpSessionBindingEvent
        ServletRequest	ServletRequestListener ServletRequestAttributeListener	ServletRequestEvent ServletRequestAttributeEvent
        -->
 */
/*
    监听ServletRequest对象的创建和销毁，在他创建或者销毁时执行一段逻辑，重写两个default方法
 */
public class ServletRequestListener1 implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
       //ServletRequest创建时
        System.out.println("ServletRequest对象被销毁了");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //ServletRequest初始化时
        System.out.println("我是Servlet监听器，ServletRequest初始化了");
    }
}
