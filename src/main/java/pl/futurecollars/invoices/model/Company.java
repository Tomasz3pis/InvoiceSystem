package pl.futurecollars.invoices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Company data")
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "Tax identification number", example = "5002009966")
    private String taxIdentificationNumber;

    @NotBlank
    @ApiModelProperty(value = "Company full name", example = "BestBuy inc.")
    private String name;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    @NotNull
    private PostalAddress address;

    public Company(){
    };

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
