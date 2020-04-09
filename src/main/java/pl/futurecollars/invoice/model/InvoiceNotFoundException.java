package pl.futurecollars.invoice.model;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(long id) {
        super("Invoice with id: " + id + " was not found");
    }
}
