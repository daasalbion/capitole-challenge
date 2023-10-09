package py.com.daas.capitolechallenge.application.controllers;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import py.com.daas.capitolechallenge.application.dtos.ErrorResponse;
import py.com.daas.capitolechallenge.domain.exceptions.DomainException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleParameterNotReadable(DateTimeParseException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(DomainException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> defaultHandler(Exception ex) {
        return new ResponseEntity<>(buildErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse buildErrorResponse(Exception ex) {
        var details = Optional.ofNullable(ex.getCause())
                .stream()
                .map(Throwable::getMessage)
                .toList();
        return new ErrorResponse(ex.getMessage(), details);
    }

}