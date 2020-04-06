package pl.futurecollars.invoices.exceptions;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(long id) {
        super("Invoice with provided id does not exist in database. "
                + "Invoice id: " + id + " not found.");
    }
}
