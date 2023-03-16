package com.springbasedconfigclass.AllBasedConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc//配置处理器控制器和处理器适配器，就是配置RequestMappingHandlerMapping和RequestMappingHandlerAdapter
@ComponentScan(basePackages = {"com.springbasedconfigclass.AllBasedConfig.controller"})//配置Controller

//和value属性作用一样，互为别名，如果不指定value或者basePackages,将默认扫描该配置类所在的包及其子包下的组件
public class MyConfig {
    @Bean
    public org.springframework.web.servlet.view.InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
