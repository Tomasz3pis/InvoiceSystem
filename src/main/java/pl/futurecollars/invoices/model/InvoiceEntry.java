package pl.futurecollars.invoices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvoiceEntry {

    @NotBlank
    private String itemName;

    private int quantity;

    @NotNull
    private BigDecimal netPrice;

    @NotNull
    private Vat vat;

    public InvoiceEntry() {
    }

    public InvoiceEntry(String itemName, int quantity, BigDecimal netPrice, Vat vat) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.netPrice = netPrice;
        this.vat = vat;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public Vat getVat() {
        return vat;
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
