package org.example.web.controller;

import org.example.service.core.dto.error.ErrorCode;
import org.example.service.core.dto.error.LocalError;
import org.example.service.core.dto.error.ResponseMultiError;
import org.example.service.core.dto.error.ResponseSingleError;
import org.example.service.core.exceptions.ConnectionAPIException;
import org.example.service.core.exceptions.ConversionTimeException;
import org.example.service.core.exceptions.NotFoundDataBaseException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler
    public ResponseEntity<ResponseMultiError> handle(MethodArgumentNotValidException e) {
        List<LocalError> localErrors = e.getBindingResult().getAllErrors()
                .stream().map(error -> new LocalError(((FieldError) error).getField(), error.getDefaultMessage())).toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMultiError(ErrorCode.STRUCTURED_ERROR, localErrors));

    }

    @ExceptionHandler
    public ResponseEntity<ResponseMultiError> handle(ConstraintViolationException e) {
        List<LocalError> localErrors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            String name = null;
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                name = node.getName();
            }
            localErrors.add(new LocalError(name, constraintViolation.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMultiError(ErrorCode.STRUCTURED_ERROR, localErrors));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseSingleError> handle(NotFoundDataBaseException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseSingleError(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(value = {ConversionTimeException.class, ConnectionAPIException.class, Exception.class})
    public ResponseEntity<ResponseSingleError> handle(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseSingleError(ErrorCode.ERROR, e.getMessage()));
    }

}
