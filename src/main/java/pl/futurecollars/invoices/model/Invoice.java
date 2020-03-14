package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.helpers.CheckIdFormat.checkIdFormat;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

    static final int HASH_OFFSET = 31;

    private String id;
    private LocalDate issueDate;
    private LocalDate saleDate;
    private Company seller;
    private Company buyer;
    private List<InvoiceEntry> entries;

    public Invoice(
            String id,
            LocalDate issueDate,
            Company seller,
            Company buyer,
            List<InvoiceEntry> entries) {
        checkForNull(id, "id");
        checkIdFormat(id);
        checkForNull(issueDate, "issueDate");
        checkForNull(seller, "seller");
        checkForNull(buyer, "buyer");
        checkForNull(entries, "entries");
        this.id = id;
        this.issueDate = issueDate;
        this.saleDate = issueDate;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public Invoice(String id,
                   LocalDate issueDate,
                   LocalDate saleDate,
                   Company seller,
                   Company buyer,
                   List<InvoiceEntry> entries) {
        checkForNull(id, "id");
        checkIdFormat(id);
        checkForNull(issueDate, "issueDate");
        checkForNull(saleDate, "saleDate");
        checkForNull(seller, "seller");
        checkForNull(buyer, "buyer");
        checkForNull(entries, "entries");
        this.id = id;
        this.issueDate = issueDate;
        this.saleDate = saleDate;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public String getId() {
        return id;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Invoice invoice = (Invoice) o;

        if (!id.equals(invoice.id)) {
            return false;
        }
        if (!issueDate.equals(invoice.issueDate)) {
            return false;
        }
        if (!saleDate.equals(invoice.saleDate)) {
            return false;
        }
        if (!seller.equals(invoice.seller)) {
            return false;
        }
        if (!buyer.equals(invoice.buyer)) {
            return false;
        }
        return entries.equals(invoice.entries);
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = HASH_OFFSET * result + issueDate.hashCode();
        result = HASH_OFFSET * result + saleDate.hashCode();
        result = HASH_OFFSET * result + seller.hashCode();
        result = HASH_OFFSET * result + buyer.hashCode();
        result = HASH_OFFSET * result + entries.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nInvoice{\n"
                + "id        = " + id + ",\n"
                + "issueDate = " + issueDate + ",\n"
                + "saleDate  = " + saleDate + ",\n"
                + "seller    = " + seller + ",\n"
                + "buyer     = " + buyer + ",\n"
                + "entries   = " + entries + "\n"
                + '}';
    }
}
