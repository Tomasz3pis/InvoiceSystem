package pl.futurecollars.invoice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

public class Invoice {

    private long id;
    private Company seller;
    private Company buyer;
    private List<InvoiceEntry> entries;
    private LocalDate data;

    private Invoice(final Company seller, final Company buyer, final LocalDate data, final List<InvoiceEntry> entries) {
        this.seller = seller;
        this.buyer = buyer;
        this.data = data;
        this.entries = entries;
    }

    Invoice() {
    }

    public BigDecimal getTotalValueWithoutVat() {
        return entries.stream()
                .map(InvoiceEntry::getTotalValueWithoutVat)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalValueWithVat() {
        return entries.stream()
                .map(InvoiceEntry::getTotalValueWithVat)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public static class InvoiceBuilder {

        private Invoice invoice = new Invoice();

        public InvoiceBuilder withSeller(Company company) {
            this.invoice.setSeller(company);
            return this;
        }

        public InvoiceBuilder withBuyer(Company company) {
            this.invoice.setBuyer(company);
            return this;
        }

        public InvoiceBuilder withDate(LocalDate date) {
            this.invoice.setData(date);
            return this;
        }

        public InvoiceBuilder withEntries(List<InvoiceEntry> entries) {
            this.invoice.setEntries(entries);
            return this;
        }

        public Invoice build() {
            if (invoice.getBuyer() == null
                    || invoice.getSeller() == null
                    || invoice.getData() == null
                    || invoice.getEntries() == null) {
                throw new InvalidParameterException("Parameters cannot be null " + invoice.toString());
            }
            return invoice;
        }
    }
}
