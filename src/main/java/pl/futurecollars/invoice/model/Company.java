package pl.futurecollars.invoice.model;

public class Company {

    public static class Builder {
        private String name;
        private String taxIdentificationNumber;
        private String streetName;
        private String postCode;
        private String town;
        private Company company;

        public String getName() {
            return name;
        }

        public Company.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Company.Builder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
            return this;
        }

        public Company.Builder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Company.Builder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Company.Builder withTown(String town) {
            this.town = town;
            return this;
        }

        public Company build() {
            return company;
        }
    }

    public String getName() {
        return name;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getTown() {
        return town;
    }

    private String name;
    private String taxIdentificationNumber;
    private String streetName;
    private String postCode;
    private String town;

    private Company() {

    }

}
