package pl.futurecollars.invoice.model;

import java.util.Objects;

public class Company {
    private String name;
    private String taxIdentificationNumber;
    private String streetName;
    private String postCode; // Postal Code verification using regex
    private String Town;

    public Company(String name, String taxIdentificationNumber, String streetName, String postCode, String town) {
        this.name = name;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.streetName = streetName;
        this.postCode = postCode;
        Town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
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
        return Objects.equals(name, company.name) &&
                Objects.equals(taxIdentificationNumber, company.taxIdentificationNumber) &&
                Objects.equals(streetName, company.streetName) &&
                Objects.equals(postCode, company.postCode) &&
                Objects.equals(Town, company.Town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxIdentificationNumber, streetName, postCode, Town);
    }

    @Override
    public String toString() {
        return "Company{" + "name='" + name + '\'' + ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' + ", streetName='" + streetName + '\'' + ", postCode='" + postCode + '\'' + ", Town='" + Town + '\'' + '}';
    }
}
