package pl.futurecollars.invoices.model;

import java.math.BigDecimal;

public enum Vat {

    VAT_23(BigDecimal.valueOf(23)),
    VAT_8(BigDecimal.valueOf(8)),
    VAT_7(BigDecimal.valueOf(7)),
    VAT_5(BigDecimal.valueOf(5)),
    VAT_0(BigDecimal.valueOf(0)),
    VAT_ZW(BigDecimal.valueOf(0));

    private BigDecimal rate;

    Vat(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
