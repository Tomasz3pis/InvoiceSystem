package pl.futurecollars.invoice.model;

import java.math.BigDecimal;

public enum Vat {

    VAT_0(new BigDecimal("0")),
    VAT_5(new BigDecimal("1.05")),
    VAT_8(new BigDecimal("1.08")),
    VAT_17(new BigDecimal("1.17")),
    VAT_23(new BigDecimal("1.23")),
    VAT_ZW(new BigDecimal("0"));

    private BigDecimal rate;

    Vat(final BigDecimal vatRate) {
        rate = vatRate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
