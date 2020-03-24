package pl.coderstrust.invoice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private long id;
    private Company seller;
    private Company buyer;
    private List<InvoiceEntry> entries;
    private LocalDate data;

    public Invoice(final Company seller, final Company buyer, final LocalDate data, final List<InvoiceEntry> entries) {
        this.seller = seller;
        this.buyer = buyer;
        this.data = data;
        this.entries = entries;
    }

    public final long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public final Company getSeller() {
        return seller;
    }

    public final void setSeller(final Company seller) {
        this.seller = seller;
    }

    public final List<InvoiceEntry> getEntries() {
        return entries;
    }

    public final void setEntries(final List<InvoiceEntry> entries) {
        this.entries = entries;
    }

    public final Company getBuyer() {
        return buyer;
    }

    public final void setBuyer(final Company buyer) {
        this.buyer = buyer;
    }

    public final LocalDate getData() {
        return data;
    }

    public final void setData(final LocalDate data) {
        this.data = data;
    }

    @Override
    public final String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Invoice)) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return EqualsBuilder.reflectionEquals(this, invoice);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
