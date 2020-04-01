package pl.futurecollars.invoice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvoiceEntry {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Vat vatRate;

    @Min(1)
    private long quantity;

    public InvoiceEntry(final String description, final BigDecimal unitPrice, final Vat vatRate, final long quantity) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    public BigDecimal getTotalValueWithoutVat() {
        return new BigDecimal(String.valueOf(unitPrice))
                .multiply(new BigDecimal(String.valueOf(quantity)));
    }

    public BigDecimal getTotalValueWithVat() {
        return new BigDecimal(String.valueOf(unitPrice))
                .multiply(BigDecimal.valueOf(quantity))
                .multiply(vatRate.getRate());
    }

    public final String getDescription() {
        return description;
    }

    public final BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public final Vat getVatRate() {
        return vatRate;
    }

    public long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof InvoiceEntry)) {
            return false;
        }
        InvoiceEntry invoiceEntry = (InvoiceEntry) o;
        return EqualsBuilder.reflectionEquals(this, invoiceEntry);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
