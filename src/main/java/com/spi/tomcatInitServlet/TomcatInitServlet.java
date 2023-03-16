package com.spi.tomcatInitServlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.Set;

public class TomcatInitServlet implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

    }
}
