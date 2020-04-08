package pl.futurecollars.invoices.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionAdviceProvider {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(
                "Path variable validation failed with message: " + exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    ResponseEntity<String> handleInvoiceNotFoundException(InvoiceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}
