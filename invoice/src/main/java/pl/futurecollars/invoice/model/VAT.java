package pl.futurecollars.invoice.model;

import java.math.BigDecimal;

public enum VAT {
    VAT_23(23), VAT_8(8), VAT_7(7), VAT_5(5), VAT_0(0), VAT_np(0), VAT_zw(0);

    private BigDecimal value;

    VAT(final long value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
