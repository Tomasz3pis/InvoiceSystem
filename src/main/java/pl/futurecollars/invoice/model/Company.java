package pl.futurecollars.invoice.model;

import java.util.Objects;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Builder builder = (Builder) o;
            return Objects.equals(name, builder.name)
                    && Objects.equals(taxIdentificationNumber, builder.taxIdentificationNumber)
                    && Objects.equals(streetName, builder.streetName)
                    && Objects.equals(postCode, builder.postCode)
                    && Objects.equals(town, builder.town)
                    && Objects.equals(company, builder.company);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, taxIdentificationNumber, streetName, postCode, town, company);
        }

        @Override
        public String toString() {
            return "Builder{"
                    + "name='"
                    + name
                    + '\''
                    + ", taxIdentificationNumber='"
                    + taxIdentificationNumber
                    + '\''
                    + ", streetName='"
                    + streetName
                    + '\''
                    + ", postCode='"
                    + postCode
                    + '\''
                    + ", town='"
                    + town
                    + '\''
                    + ", company="
                    + company
                    + '}';
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
