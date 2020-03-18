package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.regex.Pattern;

public class PostalAddress {

    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String postalCode;
    private String city;

    public PostalAddress(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        checkForNull(streetName, "streetName");
        checkForNull(streetNumber, "streetNumber");
        checkForNull(apartmentNumber, "apartmentNumber");
        checkForNull(postalCode, "postalCode");
        verifyPostalCode(postalCode);
        checkForNull(city, "city");
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    private void verifyPostalCode(String codeToCheck) {
        Pattern postalCodePattern = Pattern.compile("(\\d){2}-[\\d]{3}");
        if (!postalCodePattern.matcher(codeToCheck).matches()) {
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
        checkForNull(streetName, "streetName");
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        checkForNull(streetNumber, "streetNumber");
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        checkForNull(apartmentNumber, "apartmentNumber");
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        checkForNull(postalCode, "postalCode");
        verifyPostalCode(postalCode);
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        checkForNull(city, "city");
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
