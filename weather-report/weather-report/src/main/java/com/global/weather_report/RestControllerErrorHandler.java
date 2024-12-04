package com.global.weather_report;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

public interface RestControllerErrorHandler {
    Map<String,Object> emptyResponse = new HashMap<>();
    @ExceptionHandler(MethodArgumentNotValidException.class)
    default ResponseEntity<Object> validationErrorHandler(HttpServletRequest req, MethodArgumentNotValidException ex) {
        var response = new CommonResponse(emptyResponse, ex.getAllErrors().get(0).getDefaultMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    default ResponseEntity<Object> missingHeaderErrorHandler(HttpServletRequest req, MissingRequestHeaderException ex) {
        var response = new CommonResponse(emptyResponse, "Missing required parameter " + ex.getHeaderName(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    default ResponseEntity<Object> typeMismatchErrorHandler(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        var response = new CommonResponse(emptyResponse, "Data type mismatch for " + ex.getName(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    default ResponseEntity<Object> handleMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
        var response = new CommonResponse(emptyResponse, ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    default ResponseEntity<Object> handleInternalExceptions(Exception ex) {
        var response = new CommonResponse(emptyResponse, ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    default ResponseEntity<Object> handleConstraintExceptions(ConstraintViolationException ex) {
        var response = new CommonResponse(emptyResponse, ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    default ResponseEntity<Object> handleDataIntegrityViolationExceptions(DataIntegrityViolationException ex) {
        var response = new CommonResponse(emptyResponse,  ex.getLocalizedMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
