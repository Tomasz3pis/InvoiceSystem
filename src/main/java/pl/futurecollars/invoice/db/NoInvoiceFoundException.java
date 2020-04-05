package pl.futurecollars.invoice.db;

public class NoInvoiceFoundException extends Exception {

     public NoInvoiceFoundException(String errorMessage) {
        super(errorMessage);
    }

}
