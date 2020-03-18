package pl.futurecollars.invoice.model;

public class CompanyProvider {

    public static class Builder {
        private String name;
        private String taxIdentificationNumber;
        private String streetName;
        private String postCode;
        private String town;
        private CompanyProvider company;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
            return this;
        }

        public Builder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder withTown(String town) {
            this.town = town;
            return this;
        }

        public CompanyProvider build() {
            return company;
        }
    }
}
