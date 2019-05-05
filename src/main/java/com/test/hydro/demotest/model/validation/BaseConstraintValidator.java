package com.test.hydro.demotest.model.validation;

import javax.validation.ConstraintValidatorContext;

public class BaseConstraintValidator {

    private ConstraintValidatorContext constraintValidator;

    public ConstraintValidatorContext getConstraintValidator() {
        return constraintValidator;
    }

    public void setConstraintValidator(ConstraintValidatorContext constraintValidator) {
        this.constraintValidator = constraintValidator;
    }

    public void setMessage (String code, String message){

        if (constraintValidator==null) {
            throw new UnsupportedOperationException("Le contexte validateur est null.");
        }

        constraintValidator.disableDefaultConstraintViolation();

        constraintValidator.buildConstraintViolationWithTemplate(code + ":" + message);

    }
}
