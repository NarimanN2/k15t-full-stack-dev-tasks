package com.k15t.pat.exception;

import com.k15t.pat.dto.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleUnknownException(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        ExceptionDto exceptionDto = new ExceptionDto(Collections.singletonList("An unknown error occurred."), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> messages = new LinkedList<>();

        for (FieldError fieldError : fieldErrors)
            messages.add(fieldError.getDefaultMessage());

        ExceptionDto exceptionDto = new ExceptionDto(messages, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> handleUniqueConstraintViolationException(UniqueConstraintViolationException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        ExceptionDto exceptionDto = new ExceptionDto(Collections.singletonList(exception.getMessage()), HttpStatus.CONFLICT);
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }
}
