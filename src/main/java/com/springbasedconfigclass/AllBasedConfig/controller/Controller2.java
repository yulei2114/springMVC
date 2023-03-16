package com.springbasedconfigclass.AllBasedConfig.controller;

import com.springbasedxml.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "c2")
public class Controller2 {
    @RequestMapping("/Controller2")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("Tom",new User(20,"Tom"));
        return modelAndView;
    }
}
