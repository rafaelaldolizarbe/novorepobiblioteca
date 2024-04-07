package thelibrary.api.biblioteca.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException except){
        var errors = except.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(error -> new ErrorMessage(error.getField(),error.getDefaultMessage())).toList());
    }

    private record ErrorMessage(String field,String defaultMessage) {
    }


}
