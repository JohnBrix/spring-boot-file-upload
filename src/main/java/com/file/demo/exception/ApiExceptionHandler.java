package com.file.demo.exception;

import com.file.demo.util.UriUtil;
/*import org.hibernate.exception.ConstraintViolationException;*/
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//global controller advice
@RestControllerAdvice
public class ApiExceptionHandler {
    //si api error response mo tumatawag sa mga gnto format if exception ayan lalabas

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse resourceNotFoundException(ResourceNotFoundException exception) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), exception.getMessages(), UriUtil.path());
    }
    //ito naman pag UserNotFound sa login
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse userNotFoundException(UserNotFoundException exception) {
        return new ApiErrorResponse(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), exception.getMessages(), UriUtil.path());
    }
    //pag else default ito lalabas
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleUserNotFoundException(Exception exception) {
        List<Message> messages = Arrays.asList(new Message(exception.getMessage()));
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), messages, UriUtil.path());
    }
    //IF MY KAPAREHO VALUE and unique yung value na pinasok example gmit mo na si pomoyjohnbrix@gmail.com
    //bawal na sya reuse kaya magiging gnto response
   /* @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiErrorResponse constraintException(ConstraintViolationException exception) {
        List<Message> messages = Arrays.asList(new Message(exception.getMessage()));
        return new ApiErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now(), messages, UriUtil.path());
    }*/
}
