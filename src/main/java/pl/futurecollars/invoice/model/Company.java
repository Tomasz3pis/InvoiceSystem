package pl.futurecollars.invoice.model;

public class Company {

    private String name;
    private String taxIdentificationNumber;
    private String streetName;
    private String postCode;
    private String town;

    public Company() {

    }
    public Company(String name, String taxIdentificationNumber, String streetName, String postCode, String town) {
        this.name = name;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.streetName = streetName;
        this.postCode = postCode;
        this.town = town;
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

    public static class Builder {
    private Company company;

        public Company.Builder withName(String name) {
            company.setName(name);
            return this;
        }

        public Company.Builder withTaxIdentificationNumber(String taxIdentificationNumber) {
            company.setTaxIdentificationNumber(taxIdentificationNumber);
            return this;
        }

        public Company.Builder withStreetName(String streetName) {
            company.setStreetName(streetName);
            return this;
        }

        public Company.Builder withPostCode(String postCode) {
            company.setPostCode(postCode);
            return this;
        }

        public Company.Builder withTown(String town) {
            company.setTown(town);
            return this;
        }

        public Company build() {
           return company;
        }
    }



}
