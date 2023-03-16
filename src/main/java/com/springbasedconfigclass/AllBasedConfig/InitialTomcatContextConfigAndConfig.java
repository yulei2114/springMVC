package com.springbasedconfigclass.AllBasedConfig;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 tomcat对于配置文件的加载，会去/WEB-INF包下以及子包下去找web.xml,
 像/WEB-INF/web.xml又比如
 /WEB-INF/web/web.xml
 */
public class InitialTomcatContextConfigAndConfig implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //创建基于注解的spring容器
        AnnotationConfigWebApplicationContext dispatcherContext=new AnnotationConfigWebApplicationContext();
        //读取配置类
        dispatcherContext.register(MyConfig.class);
        //创建前端控制器，并把sprig容器传给前端控制器
        DispatcherServlet dispatcherServlet=new DispatcherServlet(dispatcherContext);
        //将前端控制器绑定到ServletContext容器中
        ServletRegistration.Dynamic dispatcher=servletContext.addServlet("diapatcher",dispatcherServlet);
        //设置前端控制器的加载时机
        dispatcher.setLoadOnStartup(1);
        //配置前端控制器的映射路径
        dispatcher.addMapping("/");
    }
}
