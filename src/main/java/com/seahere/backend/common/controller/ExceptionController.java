package com.seahere.backend.common.controller;

import com.seahere.backend.common.exception.SeaHereException;
import com.seahere.backend.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> seahereExceptionHandler(SeaHereException e) {
        int statusCode = e.getStatusCode();
        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(e.getStatusCode())
                .body(body);
    }
}