package pl.futurecollars.invoices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Company {

    @NotBlank
    private String taxIdentificationNumber;

    @NotBlank
    private String name;

    @Valid
    private PostalAddress address;

    public Company(String taxIdentificationNumber, String name, PostalAddress address) {
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.name = name;
        this.address = address;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public String getName() {
        return name;
    }

    public PostalAddress getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Company company = (Company) object;
        return EqualsBuilder.reflectionEquals(this, company);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
