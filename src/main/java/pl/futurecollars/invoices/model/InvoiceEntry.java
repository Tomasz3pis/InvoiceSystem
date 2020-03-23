package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class InvoiceEntry {

    private String itemName;
    private int quantity;
    private BigDecimal netPrice;
    private Vat vat;

    public InvoiceEntry(String itemName, int quantity, BigDecimal netPrice, Vat vat) {
        checkForNull(itemName, "itemName");
        checkForNull(netPrice, "netPrice");
        checkForNull(vat, "vat");
        this.itemName = itemName;
        this.quantity = quantity;
        this.netPrice = netPrice;
        this.vat = vat;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        checkForNull(itemName, "itemName");
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        checkForNull(netPrice, "netPrice");
        this.netPrice = netPrice;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        checkForNull(vat, "vat");
        this.vat = vat;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        InvoiceEntry entry = (InvoiceEntry) object;
        return EqualsBuilder.reflectionEquals(this, entry);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
