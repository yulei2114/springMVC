package com.springbasedconfigclass.AllBasedConfig.controller;

import com.springbasedxml.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "c1")
public class Controller1 {
    @RequestMapping("/Controller1")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView=new ModelAndView();
        //配置视图名字，后面视图解析器将根据视图名生成项目目录下的资源
        modelAndView.setViewName("success");
        /*
            如果有Model的话，一般会根据持久层逻辑拿到数据并封装成对象，也就是Model
            这里我们就模拟一个User实例
         */
        User user=new User(30,"Andy");
        //添加Model数据，这个数据模型将被绑定到request域
        modelAndView.addObject("Andy",user);
        return modelAndView;
    }
}
