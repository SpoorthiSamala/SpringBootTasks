package com.stackroute.muzixapp.controller;

import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerAdvicer {

    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> handleException(TrackAlreadyExistsException e)
    {
        return new ResponseEntity<>("Track already exists", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = TrackNotFound.class)
    public ResponseEntity<Object> handleExceptions(TrackNotFound e)
    {
        return new ResponseEntity<>("Track does not exist", HttpStatus.NOT_FOUND);

    }

}
