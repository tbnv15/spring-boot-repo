package com.learn.tbnv15.demo1.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException e) {
        System.out.println("Inside IO Exception Handler");
        System.out.println(e);
        e.printStackTrace();
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        System.out.println("Inside Normal Exception Handler");
        e.printStackTrace();
        return e.getMessage();
    }
}
