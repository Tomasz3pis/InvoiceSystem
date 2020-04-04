package pl.futurecollars.invoice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceProvider {

    private long id;
    private LocalDate issueDate;
    private List<InvoiceEntry> entries;
    private Company buyer;
    private Company seller;

    private InvoiceProvider() {
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

    private void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    private void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public Company getBuyer() {
        return buyer;
    }

    private void setBuyer(Company buyer) {
        this.buyer = buyer;
    }

    public Company getSeller() {
        return seller;
    }

    private void setSeller(Company seller) {
        this.seller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceProvider invoice = (InvoiceProvider) o;
        return Objects.equals(id, invoice.id) &&
                Objects.equals(issueDate, invoice.issueDate) &&
                Objects.equals(entries, invoice.entries) &&
                Objects.equals(buyer, invoice.buyer) &&
                Objects.equals(seller, invoice.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueDate, entries, buyer, seller);
    }

    @Override
    public String toString() {
        return "InvoiceProvider{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", entries=" + entries +
                ", buyer=" + buyer +
                ", seller=" + seller +
                '}';
    }


    public static class InvoiceBuilder {
        private InvoiceProvider invoice = new InvoiceProvider();

        InvoiceBuilder withId(long id) {
            this.invoice.setId(id);
            return this;
        }

        InvoiceBuilder withIssueDate(LocalDate issueDate) {
            this.invoice.setIssueDate(issueDate);
            return this;
        }

        InvoiceBuilder withEntries(List<InvoiceEntry> entries) {
            this.invoice.setEntries(entries);
            return this;
        }

        InvoiceBuilder withBuyer(Company buyer) {
            this.invoice.setBuyer(buyer);
            return this;
        }

        InvoiceBuilder withSeller(Company seller) {
            this.invoice.setSeller(seller);
            return this;
        }

        InvoiceProvider build() {
            return invoice;
        }

    }

}
