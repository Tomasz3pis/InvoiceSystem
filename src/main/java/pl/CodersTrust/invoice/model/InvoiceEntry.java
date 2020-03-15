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
}
