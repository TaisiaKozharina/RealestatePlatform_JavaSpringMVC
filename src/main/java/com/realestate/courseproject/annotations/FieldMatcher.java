package com.realestate.courseproject.annotations;

import com.realestate.courseproject.validators.FieldsMatcherValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//Custom annotation, that is used in registration form, checking is password == repeat password etc.
@Constraint(validatedBy = FieldsMatcherValidator.class) //location of actual validation implementation
@Target({ ElementType.TYPE }) //to use on top of the class, not for every field. See User.java
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatcher {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field(); //storing field1 - original

    String fieldMatch(); //storing field2 - repeated

    //Below code is needed because annotation will be used for 2 fields, not 1. So they are grouped in list
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldMatcher[] value();
    }
}
