package pl.futurecollars.invoices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.futurecollars.invoices.exceptions.InvoiceNotCompleteException;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Invoice object")
@Document(collection = "invoices")
public class Invoice {

    @Id
    private long id;

    @NotNull
    @ApiModelProperty(value = "The date this invoice was issued at.", example = "2020-01-10")
    private LocalDate issueDate;

    @NotNull
    @ApiModelProperty(value = "The date of an actual sale or service.", example = "2020-01-09")
    private LocalDate saleDate;

    @Valid
    private Company seller;

    @Valid
    private Company buyer;

    private List<@Valid InvoiceEntry> entries;

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Company getSeller() {
        return seller;
    }

    public void setSeller(Company seller) {
        this.seller = seller;
    }

    public Company getBuyer() {
        return buyer;
    }

    public void setBuyer(Company buyer) {
        this.buyer = buyer;
    }

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public static class InvoiceBuilder {
        private Invoice builderInvoice = new Invoice();

        public InvoiceBuilder setIssueDate(LocalDate issueDate) {
            this.builderInvoice.setIssueDate(issueDate);
            return this;
        }

        public InvoiceBuilder setId(long id) {
            this.builderInvoice.setId(id);
            return this;
        }

        public InvoiceBuilder setSaleDate(LocalDate saleDate) {
            this.builderInvoice.setSaleDate(saleDate);
            return this;
        }

        public InvoiceBuilder setSeller(Company seller) {
            this.builderInvoice.setSeller(seller);
            return this;
        }

        public InvoiceBuilder setBuyer(Company buyer) {
            this.builderInvoice.setBuyer(buyer);
            return this;
        }

        public InvoiceBuilder setEntries(List<InvoiceEntry> entries) {
            this.builderInvoice.setEntries(entries);
            return this;
        }

        public Invoice build() {
            validateInvoiceFields();
            return builderInvoice;
        }

        private void validateInvoiceFields() {
            StringBuilder missingFields = new StringBuilder();
            if (this.builderInvoice.getIssueDate() == null) {
                missingFields.append(" issueDate");
            }
            if (this.builderInvoice.getSaleDate() == null) {
                missingFields.append(" saleDate");
            }
            if (this.builderInvoice.getSeller() == null) {
                missingFields.append(" seller");
            }
            if (this.builderInvoice.getBuyer() == null) {
                missingFields.append(" buyer");
            }
            if (this.builderInvoice.getEntries() == null) {
                missingFields.append(" entries");
            }
            if (missingFields.length() != 0) {
                throw new InvoiceNotCompleteException(missingFields.toString());
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) object;
        return EqualsBuilder.reflectionEquals(this, invoice);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
