package pl.futurecollars.invoice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

    private String description;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    private Vat vat;

    private InvoiceEntry() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
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
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(pricePerUnit, that.pricePerUnit) &&
                vat == that.vat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, quantity, pricePerUnit, vat);
    }

    @Override
    public String toString() {
        return "InvoiceEntry{" +
                "description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", vat=" + vat +
                '}';
    }

    public static class InvoiceEntryBuilder {

        private InvoiceEntry invoiceEntry = new InvoiceEntry();

        InvoiceEntryBuilder withProductName(String description) {
            this.invoiceEntry.setDescription(description);
            return this;
        }

        InvoiceEntryBuilder withQuantity(Integer quantity) {
            this.invoiceEntry.setQuantity(quantity);
            return this;
        }

        InvoiceEntryBuilder withPricePerUnit(BigDecimal pricePerUnit) {
            this.invoiceEntry.setPricePerUnit(pricePerUnit);
            return this;
        }

        InvoiceEntryBuilder withVat(Vat vat) {
            this.invoiceEntry.setVat(vat);
            return this;
        }

        InvoiceEntry build() {
            return invoiceEntry;
        }
    }

}
