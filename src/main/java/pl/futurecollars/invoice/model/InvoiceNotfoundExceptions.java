package pl.futurecollars.invoice.model;

public class InvoiceNotfoundExceptions extends Exception {

    public InvoiceNotfoundExceptions(String errorMessage) {
        super(errorMessage);
    }

}
