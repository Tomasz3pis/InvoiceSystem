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

    public Invoice(final Company seller, final Company buyer, final LocalDate data, final List<InvoiceEntry> entries) {
        id = idCount.getAndIncrement();
        this.seller = seller;
        this.buyer = buyer;
        this.data = data;
        this.entries = entries;
    }

    public final long getId() {
        return id;
    }

    public final void setId(final long id) {
        this.id = id;
    }

    public final Company getSeller() {
        return seller;
    }

    public final void setSeller(final Company seller) {
        this.seller = seller;
    }

    public final Company getBuyer() {
        return buyer;
    }

    public final void setBuyer(final Company buyer) {
        this.buyer = buyer;
    }

    public final void setEntries(final List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public final LocalDate getData() {
        return data;
    }

    public final void setData(final LocalDate data) {
        this.data = data;
    }

    @Override
    public final String toString() {
        return "Invoice{"
                + "id=" + id
                + ", seller=" + seller
                + ", buyer=" + buyer
                + ", entries=" + entries
                + ", data=" + data
                + '}';
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id)
                && Objects.equals(seller, invoice.seller)
                && Objects.equals(buyer, invoice.buyer)
                && Objects.equals(entries, invoice.entries)
                && Objects.equals(data, invoice.data);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, seller, buyer, entries, data);
    }
}
