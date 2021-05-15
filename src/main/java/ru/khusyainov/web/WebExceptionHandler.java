package ru.khusyainov.web;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getNotFoundException(RuntimeException e) {
        System.out.println("Getting Error!");
        e.printStackTrace(System.out);
        return e.getMessage().replace(" class ", " ");
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String deleteNotFoundException(RuntimeException e) {
        System.out.println("Deleting Error!");
        e.printStackTrace(System.out);
        return e.getMessage().replace(" class ", " ");
    }
}