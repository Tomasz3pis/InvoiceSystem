package pl.futurecollars.invoices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "Postal address info")
public class PostalAddress {

    @NotBlank
    @ApiModelProperty(value = "Street name.", example = "Viverra Avenue")
    private String streetName;

    @NotBlank
    @ApiModelProperty(value = "Street number", example = "123B")
    private String streetNumber;

    @ApiModelProperty(value = "Apartment number (optional).", example = "12")
    private String apartmentNumber;

    @NotBlank
    @ApiModelProperty(value = "Postal code.", example = "01-101")
    private String postalCode;

    @NotBlank
    @ApiModelProperty(value = "City name.", example = "Roseville")
    private String city;

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
