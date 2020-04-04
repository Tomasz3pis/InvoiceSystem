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

    private void setName(String name) {
        this.name = name;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    private void setTaxIdentificationNumber(String taxIdentificationNumber) {
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

    private void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTown() {
        return town;
    }

    private void setTown(String town) {
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
        Company companyProvider = (Company) o;
        return Objects.equals(name, companyProvider.name) &&
                Objects.equals(taxIdentificationNumber, companyProvider.taxIdentificationNumber) &&
                Objects.equals(streetName, companyProvider.streetName) &&
                Objects.equals(postCode, companyProvider.postCode) &&
                Objects.equals(town, companyProvider.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxIdentificationNumber, streetName, postCode, town);
    }

    @Override
    public String toString() {
        return "CompanyProvider{" +
                "name='" + name + '\'' +
                ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }

    public static class CompanyBuilder {

        private Company companyProvider = new Company();

        CompanyBuilder withName(String name) {
            this.companyProvider.setName(name);
            return this;
        }

        CompanyBuilder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.companyProvider.setTaxIdentificationNumber(taxIdentificationNumber);
            return this;
        }

        CompanyBuilder withStreetName(String streetName) {
            this.companyProvider.setName(streetName);
            return this;
        }

        CompanyBuilder withPostCode(String postCode) {
            this.companyProvider.setPostCode(postCode);
            return this;
        }

        CompanyBuilder withTown(String town) {
            this.companyProvider.setTown(town);
            return this;
        }

        Company build() {
            return companyProvider;
        }

    }

}
