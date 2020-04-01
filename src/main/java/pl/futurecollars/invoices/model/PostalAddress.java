package pl.futurecollars.invoices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.regex.Pattern;
import javax.validation.constraints.NotBlank;

public class PostalAddress {

    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("(\\d){2}-[\\d]{3}");
    @NotBlank
    private String streetName;
    @NotBlank
    private String streetNumber;
    private String apartmentNumber;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String city;

    public PostalAddress(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        verifyPostalCode(postalCode);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    private void verifyPostalCode(String codeToCheck) {
        if (!POSTAL_CODE_PATTERN.matcher(codeToCheck).matches()) {
            throw new IllegalArgumentException(
                    "Provided postalCode: "
                            + codeToCheck
                            + " does not match the pattern '00-000'");
        }
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        verifyPostalCode(postalCode);
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PostalAddress postalAddress = (PostalAddress) object;
        return EqualsBuilder.reflectionEquals(this, postalAddress);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
