package pl.futurecollars.invoices.model;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.model.Invoice.HASH_OFFSET;

import java.math.BigDecimal;

public class InvoiceEntry {

    private String itemName;
    private int quantity;
    private BigDecimal netPrice;
    private Vat vat;

    public InvoiceEntry(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceEntry that = (InvoiceEntry) o;

        if (quantity != that.quantity) {
            return false;
        }
        if (!itemName.equals(that.itemName)) {
            return false;
        }
        if (!netPrice.equals(that.netPrice)) {
            return false;
        }
        return vat == that.vat;
    }

    @Override
    public int hashCode() {
        int result;
        result = itemName.hashCode();
        result = HASH_OFFSET * result + quantity;
        result = HASH_OFFSET * result + netPrice.hashCode();
        result = HASH_OFFSET * result + vat.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\n\t\t\t  InvoiceEntry{"
                + "itemName='" + itemName + '\''
                + ", quantity=" + quantity
                + ", netPrice=" + netPrice
                + ", vat=" + vat
                + '}';
    }
}
