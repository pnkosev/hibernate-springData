package pn.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pn.utils.api.ValidatorUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorUtilsImpl implements ValidatorUtils {

    private final Validator validator;

    @Autowired
    public ValidatorUtilsImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).size() == 0;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        return this.validator.validate(entity);
    }
}
