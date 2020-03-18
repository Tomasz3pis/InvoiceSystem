package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Company {

    private String taxIdentificationNumber;
    private String name;
    private PostalAddress address;

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
        Pattern taxIdentificationNumberPattern = Pattern.compile("(\\d){10}");
        if (!taxIdentificationNumberPattern.matcher(numberToVerify).matches()) {
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Company company = (Company) object;
        return EqualsBuilder.reflectionEquals(this, company);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
