package pl.futurecollars.invoice.model;

import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private String id;
    private LocalDate issueDate;
    private Company seller;
    private Company buyer;
    private List<InvoiceEntry> entries;

    public Invoice(String id, LocalDate issueDate, Company seller, Company buyer, List<InvoiceEntry> entries) {
        this.id = id;
        this.issueDate = issueDate;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
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


}
