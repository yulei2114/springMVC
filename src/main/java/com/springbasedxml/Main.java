package com.springbasedxml;


import jakarta.servlet.ServletContainerInitializer;
import org.springframework.web.HttpRequestHandler;

/**
服务器端表现层基于spring的mvc架构配置1：完全基于xml文件
1：配置DispatcherServlet:springmvc的核心，负责根据请求处理资源，本质就是一个Servlet，只不过结合了springIOC，用于集中处理请求，
并调度内置于该servlet的spring容器（管理控制器Controller/Handler），所以在配置为服务器端（Tomcat）Servlet时，会给该DispatcherServlet
配置spring容器,更确切地说是配置Controller/Handler（springbean）
2.在此之前，首先要明白：对于tomcat服务器来说，对于所有的请求，都会有对应的Servlet来处理
        可以看到在tomcat服务器config文件夹下的web.xml文件有两个Servlet：
        <!--  The mapping for the default servlet  -->
        <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>/</url-pattern>
        </servlet-mapping>
        <!--  The mappings for the JSP servlet  -->
        <servlet-mapping>
        <servlet-name>jsp</servlet-name>
        <url-pattern>*.jsp</url-pattern>
        <url-pattern>*.jspx</url-pattern>
        </servlet-mapping>
        很明显：url-pattern为"/"为优先级最低的url匹配，当你的web应用没有配置Servlet或者说配置的Servlet没有匹配到浏览器的URL时
                这个Servlet将用于处理请求
        那么：url-pattern为"*.jsp"和"*.jspx"的Servlet，将用于处理匹配扩展名为jsp和jspx的文件。
对于tomcat架构以及处理url的细节，理解还不够透彻，后面再补上吧
3.tomcat对于url的匹配规则：
    ①：精确匹配
    即url完全匹配Servlet配置的url-pattern
    example：<url-pattern>/user/myServlet1<url-pattern>
    那么url：http://localhost:8080/mySprignMVC/user/myServlet1将作为唯一结果匹配
    ②：路径匹配
    ！！！注意一点，路径匹配和后缀名匹配不能同时进行设置，比如：1./user/*.jsp，tomcat
                                                        2. /*.jsp
                                                        3./he*.jsp在启动时会报错
    以"/"开头，（"/*"）结尾的url-pattern，
    example：<url-pattern>/users/*</url-pattern>
    那么url：http://localhost:8080/mySpringMVC/users/user/index.jsp
    或者url：http://localhost:8080/mySpringMVC/users/index.jsp
    又或者url：http://localhost:8080/mySpringMVC/users/user
    控制台测试正常打印
    example：<url-pattern>/*</url-pattern>,该url为路径匹配中优先级最低的匹配路径，即所有url都可以匹配
    大可不必越俎代庖，jsp文件的请求就应该交给tomcat默认的Servlet处理，一般不用
    example：<url-pattern>/</url-pattern>,可以匹配所有资源url，并且是优先级最低的url匹配，所以除了jsp文件，
    其他的Servlet和静态资源请求都可以拦截，
    因为tomcat自带的用于处理jsp的Servlet没有被覆盖
    那么url：http://localhost:8080/mySpringMVC/lalala/hello(不存在的资源)，将匹配"/"的Servlet
 ③：后缀名匹配：*.jsp
 ！！！！！匹配优先级的问题：精确匹配>路径匹配>后缀名匹配>"/"
 */


public class Main {
    public static void main(String[] args) {

    }

}
