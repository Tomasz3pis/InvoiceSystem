package pl.futurecollars.invoices.exceptions;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
