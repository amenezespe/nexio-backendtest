package com.test.hydro.demotest.model.validation;

import com.test.hydro.demotest.model.Comment;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentValidator extends BaseConstraintValidator implements ConstraintValidator<CommentValid, Comment> {

    @Override
    public void initialize(CommentValid constraintAnnotation) {
        //DO NOT NEED TREATMENT HERE - Arthur
    }

    @Override
    public boolean isValid(Comment comment, ConstraintValidatorContext context) {
        setConstraintValidator(context);

        var error = validateComment(comment);

        if ( error != null) {
            setMessage(error.getErrorCode(), error.getMessage());
            return false;
        }

        return true;
    }


    private ErrorCode validateComment (Comment comment) {

        if (StringUtils.isEmpty(comment.getIdComment())){

            return ErrorCode.ID_COMMENT_ERROR;
        }

        if (StringUtils.isEmpty(comment.getDescription())){

            return ErrorCode.DESCRIPTION_ERROR;
        }

        return null;
    }
}
