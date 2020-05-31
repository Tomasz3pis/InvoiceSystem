package pl.futurecollars.invoices.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(long id) {
        super("Company with provided id: " + id + " not found.");
    }
}
