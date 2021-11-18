package com.esdo.bepilot.Exception;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
        log.error(ex.getMessage() + " : " + request.getDescription(false));
        return new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
    }

    /**
     * InvalidException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidException(Exception ex, WebRequest request) {
        log.error(ex.getMessage() + " : " + request.getDescription(false));
        return new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
    }

    /**
     * Validation Error sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage customValidationErrorHandling(MethodArgumentNotValidException ex) {
        log.error("Validation Error : " + ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ErrorMessage(new Date(), "Validation Error",
                ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}


