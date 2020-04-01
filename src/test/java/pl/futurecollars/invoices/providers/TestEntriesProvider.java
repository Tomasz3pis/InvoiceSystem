package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.model.Vat.VAT_0;
import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_5;
import static pl.futurecollars.invoices.model.Vat.VAT_7;
import static pl.futurecollars.invoices.model.Vat.VAT_8;
import static pl.futurecollars.invoices.model.Vat.VAT_ZW;

import pl.futurecollars.invoices.model.InvoiceEntry;
import pl.futurecollars.invoices.model.Vat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestEntriesProvider {

    static List<InvoiceEntry> firstInvoiceEntries() {
        List<InvoiceEntry> firstInvoiceEntries = new ArrayList<>();
        firstInvoiceEntries.add(new InvoiceEntry("Papier XXL", 5, BigDecimal.valueOf(14.99), VAT_23));
        return firstInvoiceEntries;
    }

    static List<InvoiceEntry> secondInvoiceEntries() {
        List<InvoiceEntry> secondInvoiceEntries = new ArrayList<>();
        secondInvoiceEntries.add(new InvoiceEntry("Igła 3\"", 20, BigDecimal.valueOf(0.49), VAT_23));
        secondInvoiceEntries.add(new InvoiceEntry("Nici tapicerskie", 50, BigDecimal.valueOf(4.14), VAT_5));
        secondInvoiceEntries.add(new InvoiceEntry("Skóra niedźwiedzia", 2, BigDecimal.valueOf(1349.0), VAT_0));
        return secondInvoiceEntries;
    }

    static List<InvoiceEntry> thirdInvoiceEntries() {
        List<InvoiceEntry> thirdInvoiceEntries = new ArrayList<>();
        thirdInvoiceEntries.add(new InvoiceEntry("1256_drewn_opal_kat3_1m3", 20, BigDecimal.valueOf(0.49), VAT_7));
        thirdInvoiceEntries.add(new InvoiceEntry("999_smola_beczk_50l", 12, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("10119_wod_swiecon_250ml", 50, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("transport_max50km", 1, BigDecimal.valueOf(1349.0), VAT_8));
        return thirdInvoiceEntries;
    }

    static List<InvoiceEntry> defaultInvoiceEntries() {
        List<InvoiceEntry> defaultInvoiceEntries = new ArrayList<>();
        defaultInvoiceEntries.add(new InvoiceEntry("", 1, BigDecimal.valueOf(0.01), VAT_0));
        return defaultInvoiceEntries;
    }

    static List<InvoiceEntry> invoiceEntriesWithOffset(int offset, int numberOfEntries) {
        List<InvoiceEntry> invoiceEntries = new ArrayList<>();
        for (int i = 1; i <= numberOfEntries; i++) {
            invoiceEntries.add(new InvoiceEntry(
                    "Produkt " + offset + "_" + i,
                    i,
                    BigDecimal.valueOf(i * 1.5),
                    getVat(i)));
        }
        return invoiceEntries;
    }

    private static Vat getVat(int offset) {
        if (offset % 2 == 0) {
            return VAT_23;
        }
        if (offset % 3 == 0) {
            return VAT_8;
        }
        return VAT_ZW;
    }
}
