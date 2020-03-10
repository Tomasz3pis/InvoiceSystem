package pl.futurecollars.invoice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

    private String productName;
    private String amount;
    private BigDecimal cost;
    private VAT vat;

    public InvoiceEntry(String productName, String amount, BigDecimal cost, VAT vat) {
        this.productName = productName;
        this.amount = amount;
        this.cost = cost;
        this.vat = vat;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
        return productName.equals(that.productName) &&
                amount.equals(that.amount) &&
                cost.equals(that.cost) &&
                vat == that.vat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, amount, cost, vat);
    }
}
