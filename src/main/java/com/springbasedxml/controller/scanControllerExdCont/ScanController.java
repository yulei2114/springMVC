package com.springbasedxml.controller.scanControllerExdCont;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("scanController")//不指定也是会默认以类名首字母小写作为该bean的id
public class ScanController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
