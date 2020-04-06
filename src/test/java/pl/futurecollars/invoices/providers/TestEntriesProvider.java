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

    static List<InvoiceEntry> invoiceEntriesCountOne() {
        List<InvoiceEntry> firstInvoiceEntries = new ArrayList<>();
        firstInvoiceEntries.add(new InvoiceEntry("One and only product", 5, BigDecimal.valueOf(14.99), VAT_23));
        return firstInvoiceEntries;
    }

    static List<InvoiceEntry> invoiceEntriesCountThree() {
        List<InvoiceEntry> secondInvoiceEntries = new ArrayList<>();
        secondInvoiceEntries.add(new InvoiceEntry("first product", 20, BigDecimal.valueOf(0.49), VAT_23));
        secondInvoiceEntries.add(new InvoiceEntry("second product", 50, BigDecimal.valueOf(4.14), VAT_5));
        secondInvoiceEntries.add(new InvoiceEntry("third product", 2, BigDecimal.valueOf(1349.0), VAT_0));
        return secondInvoiceEntries;
    }

    static List<InvoiceEntry> invoiceEntriesCountFour() {
        List<InvoiceEntry> thirdInvoiceEntries = new ArrayList<>();
        thirdInvoiceEntries.add(new InvoiceEntry("product 1.", 20, BigDecimal.valueOf(0.49), VAT_7));
        thirdInvoiceEntries.add(new InvoiceEntry("product 2.", 12, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("product 3.", 50, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("product 4.", 1, BigDecimal.valueOf(1349.0), VAT_8));
        return thirdInvoiceEntries;
    }

    static List<InvoiceEntry> invoiceEntriesWithOffset(int offset, int numberOfEntries) {
        List<InvoiceEntry> invoiceEntries = new ArrayList<>();
        for (int i = 1; i <= numberOfEntries; i++) {
            invoiceEntries.add(new InvoiceEntry(
                    "SomeProduct " + offset + "_" + i,
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
