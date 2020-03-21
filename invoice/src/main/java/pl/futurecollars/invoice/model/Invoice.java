package pl.futurecollars.invoice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Invoice {
    private Long id;
    @NotNull
    private Date date;
    private Company seller;
    private Company buyer;
    @NotEmpty
    private List<InvoiceEntry> entries;

    private BigDecimal totalValue;

    public Invoice() { }

    public Invoice(Long id, Date date, Company seller, Company buyer, List<InvoiceEntry> entries) {
        this.id = id;
        this.date = date;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) &&
                Objects.equals(date, invoice.date) &&
                Objects.equals(seller, invoice.seller) &&
                Objects.equals(buyer, invoice.buyer) &&
                Objects.equals(entries, invoice.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, seller, buyer, entries);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + date +
                ", seller=" + seller +
                ", buyer=" + buyer +
                ", entries=" + entries +
                '}';
    }

    public BigDecimal getTotalValue() {
        BigDecimal sum = BigDecimal.ZERO;
        for (InvoiceEntry entry : entries) {
            sum = sum.add(entry.getValue());
        }
        return sum;
    }
}
