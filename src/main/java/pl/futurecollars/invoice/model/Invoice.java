package pl.futurecollars.invoice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Invoice {
    public static class Builder {
        private String id;
        private LocalDate issueDate;
        private List<InvoiceEntry> entries;
        private Invoice invoice;
        private Company buyer;
        private Company seller;

        public String getId() {
            return id;
        }

        public Invoice.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Invoice.Builder withIssueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Invoice.Builder withSeller(Company seller) {
            this.seller = seller;
            return this;
        }

        public Invoice.Builder withBuyer(Company buyer) {
            this.buyer = buyer;
            return this;
        }

        public Invoice.Builder withEntries(List<InvoiceEntry> entries) {
            this.entries = entries;
            return this;
        }

        public Invoice build() {
            return invoice;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Builder builder = (Builder) o;
            return Objects.equals(id, builder.id)
                    && Objects.equals(issueDate, builder.issueDate)
                    && Objects.equals(entries, builder.entries)
                    && Objects.equals(invoice, builder.invoice)
                    && Objects.equals(buyer, builder.buyer)
                    && Objects.equals(seller, builder.seller);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, issueDate, entries, invoice, buyer, seller);
        }

        @Override
        public String toString() {
            return "Builder{"
                    + "id='" + id + '\''
                    + ", issueDate=" + issueDate
                    + ", entries=" + entries
                    + ", invoice=" + invoice
                    + ", buyer=" + buyer
                    + ", seller=" + seller
                    + '}';
        }
    }

    private String id;
    private LocalDate issueDate;
    private List<InvoiceEntry> entries;
    private Invoice invoice;
    private Company buyer;
    private Company seller;

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

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Company getBuyer() {
        return buyer;
    }

    public void setBuyer(Company buyer) {
        this.buyer = buyer;
    }

    public Company getSeller() {
        return seller;
    }

    public void setSeller(Company seller) {
        this.seller = seller;
    }

    private Invoice() {
    }
}
