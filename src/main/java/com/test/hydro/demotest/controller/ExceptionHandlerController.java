package com.test.hydro.demotest.controller;

import com.google.gson.Gson;
import com.test.hydro.demotest.model.exception.MessagePayload;
import com.test.hydro.demotest.model.validation.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessagePayload doErrorMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        var messagePayload = new MessagePayload();

        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            var error = objectError.getDefaultMessage().split(":");

            messagePayload.setCode(error[0]);
            messagePayload.setMessage(error[1]);

        }

        return messagePayload;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MessagePayload doErrorException (Exception ex) {
        var messagePayload = new MessagePayload();

        messagePayload.setCode(ErrorCode.INTERNAL_ERROR.getErrorCode());
        messagePayload.setMessage(ex.getMessage());

        return messagePayload;
    }
}
