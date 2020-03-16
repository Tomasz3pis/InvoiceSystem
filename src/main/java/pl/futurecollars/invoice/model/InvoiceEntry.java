package pl.futurecollars.invoice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {
    private String description;
    private String quantity;
    private BigDecimal cost;
    private VAT vat;

    public InvoiceEntry() {
    }

    public InvoiceEntry(String entityName, String quantity, BigDecimal cost, VAT vat) {
        this.description = entityName;
        this.quantity = quantity;
        this.cost = cost;
        this.vat = vat;
    }

    public String getEntityName() {
        return description;
    }

    public void setEntityName(String entityName) {
        this.description = entityName;
    }

    public String getAmount() {
        return quantity;
    }

    public void setAmount(String amount) {
        this.quantity = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public VAT getVat() {
        return vat;
    }

    public void setVat(VAT vat) {
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
                Objects.equals(cost, that.cost) &&
                vat == that.vat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, quantity, cost, vat);
    }

    @Override
    public String toString() {
        return "InvoiceEntry{" + "productName='" + description + '\'' + ", amount='" + quantity + '\'' + ", cost=" + cost + ", vat=" + vat + '}';
    }

}
