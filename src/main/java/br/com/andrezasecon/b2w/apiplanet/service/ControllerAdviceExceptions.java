package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.exceptions.NotFoundNameException;
import br.com.andrezasecon.b2w.apiplanet.exceptions.PlanetNotFoundException;
import br.com.andrezasecon.b2w.apiplanet.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceExceptions {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleValidationExceptions(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("campo ", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlanetNotFoundException.class)
    static ResponseEntity<Object> handlePlanetNotFoundException(
            PlanetNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("mensagem", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    static ResponseEntity<Object> handlerValidationException(
            ValidationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("mensagem", ex.getError());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundNameException.class)
    static ResponseEntity<Object> handlerValidationException(
            NotFoundNameException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("mensagem", ex.getError());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
