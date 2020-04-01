package pl.futurecollars.invoice.model;

import java.math.BigDecimal;

public enum Vat {

    VAT_23(new BigDecimal(0.23)),
    VAT_8(new BigDecimal(0.08)),
    VAT_5(new BigDecimal(0.05)),
    VAT_0(new BigDecimal(0.00));

    private BigDecimal value;

    Vat(BigDecimal bigDecimal) {
    }

    public BigDecimal getValue() {
        return value;
    }

}
