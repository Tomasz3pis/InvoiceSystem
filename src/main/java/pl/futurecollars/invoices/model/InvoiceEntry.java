package pl.futurecollars.invoices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Invoice entry data")
public class InvoiceEntry {

    @NotBlank
    @ApiModelProperty(value = "Name of an item (sale unit or service name).", example = "Wireless keyboard set")
    private String itemName;

    @ApiModelProperty(value = "Number of units sold.", example = "10")
    private int quantity;

    @NotNull
    @ApiModelProperty(value = "Net price of a single sale / service unit.", example = "129.90")
    private BigDecimal netPrice;

    @NotNull
    private Vat vat;

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
