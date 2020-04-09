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
    private BigDecimal quantity;

    public InvoiceEntry(final String description, final BigDecimal unitPrice, final Vat vatRate, final BigDecimal quantity) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    public BigDecimal getTotalValueWithoutVat() {
        return  unitPrice
                .multiply(quantity);
    }

    public BigDecimal getTotalValueWithVat() {
        return unitPrice
                .multiply(quantity)
                .multiply(vatRate.getRate());
    }

    public final String getDescription() {
        return description;
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
