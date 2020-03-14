package pl.CodersTrust.invoice.model;

public class Company {
    private long taxIdentificationNumber;
    private String address;
    private String name;

    public Company(final long taxIdentificationNumber, final String address, final String name) {
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.address = address;
        this.name = name;
    }

    public final long getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public final void setTaxIdentificationNumber(final long taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(final String address) {
        this.address = address;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }
}
