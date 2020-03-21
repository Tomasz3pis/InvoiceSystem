package pl.CodersTrust.invoice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Company)) {
            return false;
        }
        Company company = (Company) o;
        return EqualsBuilder.reflectionEquals(this, company);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
