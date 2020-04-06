package pl.futurecollars.invoices.exceptions;

public class InvoiceNotCompleteException extends RuntimeException {

    public InvoiceNotCompleteException(String missingFields) {
        super("Cannot build an invoice without mandatory field:"
                + missingFields + ".");
    }
}

