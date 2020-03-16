package pl.futurecollars.invoice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

    private Long id;
    private String title;
    private BigDecimal value;
    private VAT vat;

    public InvoiceEntry() {
    }

    public InvoiceEntry(String title, BigDecimal value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public VAT getVat() {
        return vat;
    }

    public void setVat(VAT vat) {
        this.vat = vat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(title, that.title) &&
                Objects.equals(value, that.value) &&
                vat == that.vat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, value, vat);
    }

    @Override
    public String toString() {
        return "InvoiceEntry{" +
                "title='" + title + '\'' +
                ", value=" + value +
                ", vat=" + vat +
                '}';
    }
}
