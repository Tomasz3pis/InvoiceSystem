package pl.futurecollars.invoices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;

public class PostalAddress {

    @NotBlank
    private String streetName;

    @NotBlank
    private String streetNumber;

    private String apartmentNumber;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String city;

    public PostalAddress() {
    }

    public PostalAddress(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
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
