package pl.CodersTrust.invoice.model;

public enum Vat {
    VAT_0(0), VAT_5(5), VAT_8(8), VAT_17(17), VAT_23(23), VAT_ZW(0);

    private int vat;

    Vat(final int vat) {
        this.vat = vat;
    }

    public int getVat() {
        return vat;
    }
}
