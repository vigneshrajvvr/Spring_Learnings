package com.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("mvc", new DispatcherServlet((WebApplicationContext) context));
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

    }
}
