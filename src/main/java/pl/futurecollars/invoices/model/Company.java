package pl.futurecollars.invoices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Scanner;
import java.util.regex.Pattern;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Company {

    private static final Pattern TAX_IDENTIFICATION_NUMBER_PATTERN = Pattern.compile("(\\d){10}");
    @NotBlank
    private String taxIdentificationNumber;
    @NotBlank
    private String name;
    @Valid
    private PostalAddress address;

    public Company(String taxIdentificationNumber, String name, PostalAddress address) {
        verifyTaxIdentificationNumber(normalizeNumber(taxIdentificationNumber));
        this.taxIdentificationNumber = normalizeNumber(taxIdentificationNumber);
        this.name = name;
        this.address = address;
    }

    private String normalizeNumber(String numberToNormalize) {
        StringBuilder normalizedNumber = new StringBuilder();
        try (Scanner scanner = new Scanner(numberToNormalize)) {
            while (scanner.useDelimiter("[^\\d]?").hasNextInt()) {
                normalizedNumber.append(scanner.useDelimiter("[^\\d]?").nextInt());
            }
        }
        return normalizedNumber.toString();
    }

    private void verifyTaxIdentificationNumber(String numberToVerify) {
        if (!TAX_IDENTIFICATION_NUMBER_PATTERN.matcher(numberToVerify).matches()) {
            throw new IllegalArgumentException(
                    "Provided taxIdentificationNumber: "
                            + numberToVerify
                            + " does not match the ten-digit pattern with optional single-char delimiter, "
                            + "e.g. 000000000 | 000-000-00-00 | 000 000 00 00");
        }
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        verifyTaxIdentificationNumber(normalizeNumber(taxIdentificationNumber));
        this.taxIdentificationNumber = normalizeNumber(taxIdentificationNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
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
