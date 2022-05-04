package com.realestate.courseproject.validators;

import com.realestate.courseproject.annotations.FieldMatcher;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//In the ConstraintValidator<FieldMatcher, Object> - T is Object because operation is performed on 2 strings, which is not an object
public class FieldsMatcherValidator implements ConstraintValidator<FieldMatcher, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatcher constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
