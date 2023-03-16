package com.springbasedxml.controller.scanControllerAnno;

import com.springbasedxml.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControllerAnno {
    @RequestMapping("/ControllerAnno1")
    public ModelAndView modelAndView(){
        ModelAndView mav=new ModelAndView();
        //设置视图名字和Model数据（将绑定到本次请求request对象上），后面视图解析器根据返回的ModelAndView生成项目根目录下的资源文件
        // 这时前端控制器再根据Model数据将数据渲染到页面，返回给用户
        mav.setViewName("success");
        User user=new User(3,"tom");
        mav.addObject("user",user);
        System.out.println("hahaha,我是注解配置的处理器呦");
        return mav;
    }

}
