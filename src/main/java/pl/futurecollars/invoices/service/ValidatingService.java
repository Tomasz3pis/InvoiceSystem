package pl.futurecollars.invoices.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

@Service
public class ValidatingService {

    private Validator validator;

    ValidatingService(Validator validator) {
        this.validator = validator;
    }

    public void validateInput(Object input) {
        Set<ConstraintViolation<Object>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
