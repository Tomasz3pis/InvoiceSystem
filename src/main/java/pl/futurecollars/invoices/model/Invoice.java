package pl.futurecollars.invoices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class Invoice {

    private long id;
    @NotNull
    private LocalDate issueDate;
    @NotNull
    private LocalDate saleDate;
    @Valid
    private Company seller;
    @Valid
    private Company buyer;
    private List<@Valid InvoiceEntry> entries;

    public Invoice() {
    }

    private Invoice(InvoiceBuilder invoiceBuilder) {
        this.issueDate = invoiceBuilder.builderIssueDate;
        this.saleDate = invoiceBuilder.builderSaleDate;
        this.seller = invoiceBuilder.builderSeller;
        this.buyer = invoiceBuilder.builderBuyer;
        this.entries = invoiceBuilder.builderEntries;
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
        private LocalDate builderIssueDate;
        private LocalDate builderSaleDate;
        private Company builderSeller;
        private Company builderBuyer;
        private List<InvoiceEntry> builderEntries;

        public InvoiceBuilder setIssueDate(LocalDate issueDate) {
            this.builderIssueDate = issueDate;
            return this;
        }

        public InvoiceBuilder setSaleDate(LocalDate saleDate) {
            this.builderSaleDate = saleDate;
            return this;
        }

        public InvoiceBuilder setSeller(Company seller) {
            this.builderSeller = seller;
            return this;
        }

        public InvoiceBuilder setBuyer(Company buyer) {
            this.builderBuyer = buyer;
            return this;
        }

        public InvoiceBuilder setEntries(List<InvoiceEntry> entries) {
            this.builderEntries = entries;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
