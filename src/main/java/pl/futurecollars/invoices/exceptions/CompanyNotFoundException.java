package pl.futurecollars.invoices.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(long id) {
        super("Company with provided id does not exist in database. "
                + "Company id: " + id + " not found.");
    }
}
