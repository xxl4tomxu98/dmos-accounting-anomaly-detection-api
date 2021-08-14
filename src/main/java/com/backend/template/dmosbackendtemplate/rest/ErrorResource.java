package com.backend.template.dmosbackendtemplate.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorResource implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public void handleError(HttpServletRequest request) throws Throwable {
        System.out.println("ErrorResource > handle error:");
        System.out.println(request.getAttributeNames());

        if (request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
            throw (Throwable) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }
    }


//    @Override
    //public String getErrorPath() {
    //    return null;
   // }
}
