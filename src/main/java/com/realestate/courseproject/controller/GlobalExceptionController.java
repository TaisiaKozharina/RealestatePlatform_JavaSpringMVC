package com.realestate.courseproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(Exception.class) //Handles alllll exceptions, since Exception class is parent of all exceptions. Can specify type
    public ModelAndView exceptionHandler(Exception exception){
        //Can define any business logic. In this case - error html page will be displayed.
        log.error("ERR: ", exception);
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errorMessage", exception.getStackTrace());
        return errorPage;
    }
}
