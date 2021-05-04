package ru.khusyainov.rest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getNotFoundException(NoSuchElementException e) {
        System.out.println("Getting Error!");
        e.printStackTrace(System.out);
        return e.getMessage();
    }

    @ExceptionHandler({IllegalArgumentException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String deleteNotFoundException(RuntimeException e) {
        System.out.println("Deleting Error!");
        e.printStackTrace(System.out);
        return e.getMessage().replace("class ru.khusyainov.model.", "");
    }
}