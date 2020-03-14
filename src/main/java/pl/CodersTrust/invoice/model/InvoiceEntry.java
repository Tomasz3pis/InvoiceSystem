package pl.CodersTrust.invoice.model;


import java.math.BigDecimal;
import java.util.Objects;

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
    public final String toString() {
        return "InvoiceEntry{"
                + "description='" + description + '\''
                + ", value=" + value
                + ", vatRate=" + vatRate
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
        InvoiceEntry that = (InvoiceEntry) o;
        return Objects.equals(description, that.description)
                && Objects.equals(value, that.value)
                && vatRate == that.vatRate;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(description, value, vatRate);
    }
}
