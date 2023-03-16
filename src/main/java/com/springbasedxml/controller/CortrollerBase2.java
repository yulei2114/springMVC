package com.springbasedxml.controller;

import com.springbasedxml.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CortrollerBase2 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav=new ModelAndView("hello");
        User user=new User(20,"Andy");
        mav.addObject("user",user);
        return mav;
    }
}
