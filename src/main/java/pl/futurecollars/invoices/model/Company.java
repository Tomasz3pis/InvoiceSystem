package pl.futurecollars.invoices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Component
@ApiModel(description = "Company data")
public class Company {

    @NotBlank
    @ApiModelProperty(value = "Tax identification number", example = "5002009966")
    private String taxIdentificationNumber;

    @NotBlank
    @ApiModelProperty(value = "Company full name", example = "BestBuy inc.")
    private String name;

    @Valid
    private PostalAddress address;

    public Company() {
    }

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
                ToStringStyle.SIMPLE_STYLE);
    }
}
