package pl.futurecollars.invoices;

import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_7;
import static pl.futurecollars.invoices.model.Vat.VAT_ZW;

import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.InvoiceEntry;
import pl.futurecollars.invoices.model.PostalAddress;
import pl.futurecollars.invoices.model.Vat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TestInvoiceProvider {

    static Invoice getInvoice(int offset, int numberOfEntries) {

        PostalAddress sellerAddress = new PostalAddress(
                "Sprzedajna" + offset,
                "10" + String.format("%02d", offset).substring(0, 2),
                "" + String.format("%02d", offset).substring(0, 2),
                "99-" + String.format("%03d", offset).substring(0, 3),
                "Sprzedajnice" + offset);
        Company seller = new Company(
                String.format("%010d", offset).substring(0, 10),
                "John Doe Sales " + offset,
                sellerAddress);

        PostalAddress buyerAddress = new PostalAddress(
                "Pokupna" + offset,
                "90" + String.format("%02d", offset).substring(0, 2),
                "9" + String.format("%02d", offset).substring(0, 2),
                "09-" + String.format("%03d", offset).substring(0, 3),
                "Wola Kupczycka " + offset);
        Company buyer = new Company(
                String.format("9%09d", offset).substring(0, 10),
                "Jane Doe Buyers " + offset,
                buyerAddress);

        List<InvoiceEntry> invoiceEntries = new ArrayList<>();
        for (int i = 1; i <= numberOfEntries; i++) {
            invoiceEntries.add(new InvoiceEntry(
                    "Produkt " + offset + "_" + i,
                    i,
                    BigDecimal.valueOf(i * 1.5),
                    getVat(i)));
        }

        return new Invoice(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                buyer,
                invoiceEntries
        );
    }

    private static Vat getVat(int offset) {
        if (offset % 2 == 0) {
            return VAT_23;
        }
        if (offset % 3 == 0) {
            return VAT_7;
        }
        return VAT_ZW;
    }
}
