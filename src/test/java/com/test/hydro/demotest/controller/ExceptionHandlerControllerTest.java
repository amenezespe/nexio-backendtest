package com.test.hydro.demotest.controller;

import com.test.hydro.demotest.model.Comment;
import com.test.hydro.demotest.model.exception.MessagePayload;
import com.test.hydro.demotest.model.validation.ErrorCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerControllerTest {

    @InjectMocks
    ExceptionHandlerController controllerException;


    @Test
    public void testeErrorException() {

        Exception exception = new Exception("Test Exception");
        MessagePayload error = controllerException.doErrorException(exception);

        Assert.assertEquals(error.getCode(), ErrorCode.INTERNAL_ERROR.getErrorCode());

    }

    private static final String OBJECT_WITH_VALIDATION_ERROR = "Comment";

//    @Test
//    public void test() throws Exception {
//        mockMvc.perform(post("/post/111/comment").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
//    }
//
    @Test
    public void testErrorParseException() throws Exception{

        MethodArgumentNotValidException ex = createExceptionWithFieldErrors();
        MessagePayload error = controllerException.doErrorMethodArgumentNotValidException(ex);

        Assert.assertEquals(error.getCode(), ErrorCode.DESCRIPTION_ERROR.getErrorCode());

    }

    private MethodArgumentNotValidException createExceptionWithFieldErrors() {
        BindingResult bindingResult = new BeanPropertyBindingResult(new Comment(), OBJECT_WITH_VALIDATION_ERROR);

            FieldError fieldError = new FieldError("Comment", "description", "10002:Test");
            bindingResult.addError(fieldError);


        return new MethodArgumentNotValidException(Mockito.mock(MethodParameter.class), bindingResult);
    }

}
