package pl.futurecollars.invoice.model;

import java.util.Objects;

public class CompanyProvider {

    private String name;
    private String taxIdentificationNumber;
    private String streetName;
    private String postCode;
    private String town;

    private CompanyProvider() {
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
        CompanyProvider companyProvider = (CompanyProvider) o;
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

        private CompanyProvider CompanyProvider = new CompanyProvider();

        CompanyBuilder withName(String name) {
            this.CompanyProvider.setName(name);
            return this;
        }

        CompanyBuilder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.CompanyProvider.setTaxIdentificationNumber(taxIdentificationNumber);
            return this;
        }

        CompanyBuilder withStreetName(String streetName) {
            this.CompanyProvider.setName(streetName);
            return this;
        }

        CompanyBuilder withPostCode(String postCode) {
            this.CompanyProvider.setPostCode(postCode);
            return this;
        }

        CompanyBuilder withTown(String town) {
            this.CompanyProvider.setTown(town);
            return this;
        }

        CompanyProvider build() {
            return CompanyProvider;
        }

    }

}
