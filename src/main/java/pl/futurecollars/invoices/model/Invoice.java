package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

    private Long id;
    private LocalDate issueDate;
    private LocalDate saleDate;
    private Company seller;
    private Company buyer;
    private List<InvoiceEntry> entries;

    public Invoice(LocalDate issueDate, Company seller, Company buyer, List<InvoiceEntry> entries) {
        checkForNull(issueDate, "issueDate");
        checkForNull(seller, "seller");
        checkForNull(buyer, "buyer");
        checkForNull(entries, "entries");
        this.issueDate = issueDate;
        this.saleDate = issueDate;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public Invoice(LocalDate issueDate, LocalDate saleDate, Company seller, Company buyer, List<InvoiceEntry> entries) {
        checkForNull(issueDate, "issueDate");
        checkForNull(saleDate, "saleDate");
        checkForNull(seller, "seller");
        checkForNull(buyer, "buyer");
        checkForNull(entries, "entries");
        this.issueDate = issueDate;
        this.saleDate = saleDate;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        checkForNull(id, "id");
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        checkForNull(issueDate, "issueDate");
        this.issueDate = issueDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        checkForNull(saleDate, "saleDate");
        this.saleDate = saleDate;
    }

    public Company getSeller() {
        return seller;
    }

    public void setSeller(Company seller) {
        checkForNull(seller, "seller");
        this.seller = seller;
    }

    public Company getBuyer() {
        return buyer;
    }

    public void setBuyer(Company buyer) {
        checkForNull(buyer, "buyer");
        this.buyer = buyer;
    }

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntry> entries) {
        checkForNull(entries, "entries");
        this.entries = entries;
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
