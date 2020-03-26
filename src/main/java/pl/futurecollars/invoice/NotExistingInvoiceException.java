package pl.futurecollars.invoice;

public class NotExistingInvoiceException extends RuntimeException {

    public NotExistingInvoiceException(String errorMessage) {
        super(errorMessage);
    }
}
