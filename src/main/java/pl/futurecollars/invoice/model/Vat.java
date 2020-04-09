package pl.futurecollars.invoice.model;

import java.math.BigDecimal;

public enum Vat {

    VAT_0("0"),
    VAT_5("1.05"),
    VAT_8("1.08"),
    VAT_17("1.17"),
    VAT_23("1.23"),
    VAT_ZW("0");

    private BigDecimal rate;

    Vat(final String vatRate) {
        rate = new BigDecimal(vatRate);
    }

    public BigDecimal getRate() {
        return rate;
    }
}
