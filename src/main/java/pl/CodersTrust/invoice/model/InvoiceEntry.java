package pl.CodersTrust.invoice.model;


import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {
    private String description;
    private BigDecimal value;
    private Vat vatRate;

    public InvoiceEntry(String description, BigDecimal value, Vat vatRate) {
        this.description = description;
        this.value = value;
        this.vatRate = vatRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Vat getVatRate() {
        return vatRate;
    }

    public void setVatRate(Vat vatRate) {
        this.vatRate = vatRate;
    }


    @Override
    public String toString() {
        return "InvoiceEntry{" +
                "description='" + description + '\'' +
                ", value=" + value +
                ", vatRate=" + vatRate +
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
        InvoiceEntry that = (InvoiceEntry) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(value, that.value) &&
                vatRate == that.vatRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, value, vatRate);
    }
}
