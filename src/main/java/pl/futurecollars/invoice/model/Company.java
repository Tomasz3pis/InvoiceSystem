package pl.futurecollars.invoice.model;

import java.util.Objects;

public class Company {

    private String name;
    private String taxIdentificationNumber;
    private String streetName;
    private String postCode;
    private String town;

    private Company() {
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
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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
                Objects.equals(town, company.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxIdentificationNumber, streetName, postCode, town);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }

    public static class CompanyBuilder {

        private Company company = new Company();

        CompanyBuilder withName(String name) {
            this.company.setName(name);
            return this;
        }

        CompanyBuilder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.company.setTaxIdentificationNumber(taxIdentificationNumber);
            return this;
        }

        CompanyBuilder withStreetName(String streetName) {
            this.company.setName(streetName);
            return this;
        }

        CompanyBuilder withPostCode(String postCode) {
            this.company.setPostCode(postCode);
            return this;
        }

        CompanyBuilder withTown(String town) {
            this.company.setTown(town);
            return this;
        }

        Company build() {
            return company;
        }

    }

}
