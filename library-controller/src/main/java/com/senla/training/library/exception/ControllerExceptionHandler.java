package com.senla.training.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    public ControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEntityNotFoundException(
            EntityNotFoundException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.EntityNotFoundException", null, locale),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBookAlreadyDeletedException(
            BookAlreadyDeletedException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.BookAlreadyDeletedException", null, locale),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.HttpMessageNotReadableException", null, locale),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.EmptyResultDataAccessException", null, locale),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.DataIntegrityViolationException", null, locale),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.HttpMediaTypeNotSupportedException", null, locale),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.HttpRequestMethodNotSupportedException", null, locale),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(
            IllegalArgumentException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.IllegalArgumentException", null, locale),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBookOutStockException(
            BookOutStockException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.BookOutStockException", null, locale),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.NoHandlerFoundException", null, locale),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.MissingServletRequestParameterException", null, locale),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleAccessDeniedException(
            AccessDeniedException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.AccessDeniedException", null, locale),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNullPointerException(
            NullPointerException exception,
            @RequestParam("locale") Locale locale) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(messageSource.getMessage(
                "label.NullPointerException", null, locale),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
