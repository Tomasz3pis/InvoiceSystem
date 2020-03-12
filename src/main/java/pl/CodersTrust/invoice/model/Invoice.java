package pl.CodersTrust.invoice.model;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Invoice {
    private static final AtomicLong idCount = new AtomicLong();
    private long id;
    private Company seller;
    private Company buyer;
    private List<InvoiceEntry> entries;
    private LocalDate data;

    public Invoice(Company seller, Company buyer, LocalDate data, List<InvoiceEntry> entries) {
        id = idCount.getAndIncrement();
        this.seller = seller;
        this.buyer = buyer;
        this.data = data;
        this.entries = entries;
    }

//    public BigDecimal totalValue() {
//        return entries.stream()
//                .map(InvoiceEntry::getValue)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", seller=" + seller +
                ", buyer=" + buyer +
                ", entries=" + entries +
                ", data=" + data +
                '}';
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
                Objects.equals(seller, invoice.seller) &&
                Objects.equals(buyer, invoice.buyer) &&
                Objects.equals(entries, invoice.entries) &&
                Objects.equals(data, invoice.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seller, buyer, entries, data);
    }
}
