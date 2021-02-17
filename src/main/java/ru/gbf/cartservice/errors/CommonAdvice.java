package ru.gbf.cartservice.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CommonAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionHandler(ResourceNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ResourceLackException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionHandler(ResourceLackException e) {
        return e.getMessage();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors =  e.getBindingResult().getAllErrors().stream().
                map(t->t.getObjectName()+": "+t.getDefaultMessage()).collect(Collectors.toList());
        return handleExceptionInternal(e,errors, headers, HttpStatus.BAD_REQUEST, request);
    }




}
