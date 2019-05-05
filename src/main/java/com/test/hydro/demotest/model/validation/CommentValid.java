package com.test.hydro.demotest.model.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommentValidator.class)
public @interface  CommentValid {

    String message = "Le Commenraire n'est pas valide";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};
}
