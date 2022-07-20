package com.example.demo.exceptions;


import com.example.demo.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resolveException(ResourceNotFoundException ex){
        ApiResponse apiResponse = new ApiResponse(true, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> resolveException(BindException ex){
        log.info("BindException");
        Map<String, String> errors = new HashMap<>();
        if(ex.getBindingResult().hasErrors()){

            ex.getBindingResult().getFieldErrors().forEach(
                    error -> errors.put(error.getField(),error.getDefaultMessage())
            );
            String errorMessage = "";
            for (String key :
                    errors.keySet()) {
                errorMessage+= "Field: " + key + ", " + errors.get(key) + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        log.info("Field valid");
        return new ResponseEntity<>("Field valid", HttpStatus.ACCEPTED);
    }
}
