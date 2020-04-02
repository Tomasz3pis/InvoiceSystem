package pl.futurecollars.invoice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceProvider {

    private Integer id;
    private LocalDate issueDate;
    private List<InvoiceEntry> entries;
    private CompanyProvider buyer;
    private CompanyProvider seller;

    public InvoiceProvider() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public CompanyProvider getBuyer() {
        return buyer;
    }

    public void setBuyer(CompanyProvider buyer) {
        this.buyer = buyer;
    }

    public CompanyProvider getSeller() {
        return seller;
    }

    public void setSeller(CompanyProvider seller) {
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

        InvoiceBuilder withId(Integer id) {
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

        InvoiceBuilder withBuyer(CompanyProvider buyer) {
            this.invoice.setBuyer(buyer);
            return this;
        }

        InvoiceBuilder withSeller(CompanyProvider seller) {
            this.invoice.setSeller(seller);
            return this;
        }

        InvoiceProvider build() {
            return invoice;
        }

    }

}
