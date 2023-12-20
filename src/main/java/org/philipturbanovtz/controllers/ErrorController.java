package org.philipturbanovtz.controllers;

import java.util.HashMap;
import java.util.Map;
import org.philipturbanovtz.common.HttpHelper;
import org.philipturbanovtz.dtos.api.http.HttpResponseDto;
import org.philipturbanovtz.enums.ResponseStatusCodeEnum;
import org.philipturbanovtz.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<HttpResponseDto> handleApiExceptions(ApiException e) {
        HttpResponseDto response = HttpHelper.generateResponseErr(
            e.getCode(),
            e.getMessage(),
            null
        );
        return buildResponse(response, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponseDto> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        StringBuilder errMsg = new StringBuilder();
        errors.forEach((key, value) -> errMsg.append(key).append(": ").append(value).append(". "));
        HttpResponseDto response = HttpHelper.generateResponseErr(
            ResponseStatusCodeEnum.ERROR.getCode(),
            errMsg.toString(),
            null
        );
        return buildResponse(response, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<HttpResponseDto> buildResponse(HttpResponseDto response, HttpStatus status) {
        return new ResponseEntity<>(response, status);
    }
}
