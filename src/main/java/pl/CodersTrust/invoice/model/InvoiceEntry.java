package pl.CodersTrust.invoice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

public class InvoiceEntry {
    private String description;
    private BigDecimal value;
    private Vat vatRate;

    public InvoiceEntry(final String description, final BigDecimal value, final Vat vatRate) {
        this.description = description;
        this.value = value;
        this.vatRate = vatRate;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final BigDecimal getValue() {
        return value;
    }

    public final void setValue(final BigDecimal value) {
        this.value = value;
    }

    public final Vat getVatRate() {
        return vatRate;
    }

    public final void setVatRate(final Vat vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof InvoiceEntry)) {
            return false;
        }
        InvoiceEntry invoiceEntry = (InvoiceEntry) o;
        return EqualsBuilder.reflectionEquals(this, invoiceEntry);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
