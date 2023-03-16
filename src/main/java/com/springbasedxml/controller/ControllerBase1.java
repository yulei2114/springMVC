package com.springbasedxml.controller;

import com.springbasedxml.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ControllerBase1 implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //当对应url请求是希望返回一个动态（jsp）页面时，可以通过此方法返回一个视图解析器
        ModelAndView mav=new ModelAndView();
        //配置View，也就是页面的名字，后面会配合视图解析器解析成一个具体的页面
        mav.setViewName("success");
        //假如此时已经根据页面传递过来的参数获取了数据库的信息，还可以将数据封装成一个对象，比如这里用一个User类
        User user=new User(10,"Tom");
        //后面视图解析器将user对象将作为request属性（属性名为user）绑定到request上，
        mav.addObject("user",user);
        //返回视图
        return mav;
    }
}
