package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.model.Invoice.HASH_OFFSET;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Company {

    private String taxIdentificationNumber;
    private String name;
    private PostalAddress address;
    private Pattern postalCodePattern = Pattern.compile("(\\d){10}");

    public Company(
            String taxIdentificationNumber,
            String name,
            PostalAddress address) {
        checkForNull(taxIdentificationNumber, "taxIdentificationNumber");
        verifyTaxIdentificationNumber(normalizeNumber(taxIdentificationNumber));
        checkForNull(name, "name");
        checkForNull(address, "address");
        this.taxIdentificationNumber = normalizeNumber(taxIdentificationNumber);
        this.name = name;
        this.address = address;
    }

    private String normalizeNumber(String numberToNormalize) {
        StringBuilder normalizedNumber = new StringBuilder();
        try (Scanner scanner = new Scanner(numberToNormalize)) {
            while (scanner.useDelimiter("[^\\d]?").hasNextInt()) {
                normalizedNumber.append(
                        scanner.useDelimiter("[^\\d]?").nextInt());
            }
        }
        return normalizedNumber.toString();
    }

    private void verifyTaxIdentificationNumber(String numberToVerify) {
        if (!postalCodePattern.matcher(numberToVerify).matches()) {
            throw new IllegalArgumentException(
                    "Provided taxIdentificationNumber: "
                            + numberToVerify
                            + " does not match the ten-digit pattern");
        }
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        checkForNull(taxIdentificationNumber, "taxIdentificationNumber");
        verifyTaxIdentificationNumber(normalizeNumber(taxIdentificationNumber));
        this.taxIdentificationNumber = normalizeNumber(taxIdentificationNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkForNull(name, "name");
        this.name = name;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
        checkForNull(address, "address");
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        if (!taxIdentificationNumber
                .equals(company.taxIdentificationNumber)) {
            return false;
        }
        if (!name.equals(company.name)) {
            return false;
        }
        return address.equals(company.address);
    }

    @Override
    public int hashCode() {
        int result;
        result = taxIdentificationNumber.hashCode();
        result = HASH_OFFSET * result + name.hashCode();
        result = HASH_OFFSET * result + address.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Company{\n"
                + "\t\t\t  taxIdentificationNumber = "
                + taxIdentificationNumber + ", "
                + " name = " + name + ",\n"
                + "\t\t\t  address = " + address + "}";
    }
}
