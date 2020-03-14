package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.model.Invoice.HASH_OFFSET;

import java.util.regex.Pattern;

public class PostalAddress {

    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String postalCode;
    private String city;
    private Pattern postalCodePattern = Pattern.compile("(\\d){2}-[\\d]{3}");

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostalAddress that = (PostalAddress) o;

        if (!streetName.equals(that.streetName)) {
            return false;
        }
        if (!streetNumber.equals(that.streetNumber)) {
            return false;
        }
        if (!apartmentNumber.equals(that.apartmentNumber)) {
            return false;
        }
        if (!postalCode.equals(that.postalCode)) {
            return false;
        }
        return city.equals(that.city);
    }

    @Override
    public int hashCode() {
        int result;
        result = streetName.hashCode();
        result = HASH_OFFSET * result + streetNumber.hashCode();
        result = HASH_OFFSET * result + apartmentNumber.hashCode();
        result = HASH_OFFSET * result + postalCode.hashCode();
        result = HASH_OFFSET * result + city.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PostalAddress{"
                + "streetName = '" + streetName + '\''
                + ", streetNumber = '" + streetNumber + '\''
                + ", apartmentNumber = '" + apartmentNumber + '\''
                + ", postalCode = '" + postalCode + '\''
                + ", city = '" + city + '\''
                + '}';
    }
}
