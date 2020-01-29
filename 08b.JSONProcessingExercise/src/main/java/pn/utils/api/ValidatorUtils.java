package pn.utils.api;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtils {
    <T> boolean isValid(T entity);
    <T> Set<ConstraintViolation<T>> getViolations(T entity);
}
