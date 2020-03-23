package pl.futurecollars.invoices;

import static pl.futurecollars.invoices.model.Vat.VAT_0;
import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_5;
import static pl.futurecollars.invoices.model.Vat.VAT_7;
import static pl.futurecollars.invoices.model.Vat.VAT_8;
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

    static Invoice getInvoice(int presetId) {
        Invoice invoice;

        PostalAddress firstSellerAddress = new PostalAddress(
                "Sprzedajna",
                "11",
                "",
                "11-111",
                "Sprzedajnice");
        Company firstSeller = new Company(
                "1111111111",
                "FHU Hurtex Sp. z o.o.",
                firstSellerAddress);
        PostalAddress secondSellerAddress = new PostalAddress(
                "Al. Kupców Bławatnych",
                "101",
                "12b",
                "01-111",
                "Nowa Mała Wieś");
        Company secondSeller = new Company(
                "1011111111",
                "Sprzedam Wszystko Adam Kowalski",
                secondSellerAddress);
        PostalAddress thirdSellerAddress = new PostalAddress(
                "Wądołki Dolne",
                "1101",
                "",
                "10-100",
                "Zasiedmiogórogrodzie");
        Company thirdSeller = new Company(
                "1011234567",
                "SPEDEX SP. Z O.O.",
                thirdSellerAddress);
        PostalAddress defaultSellerAddress = new PostalAddress(
                "",
                "",
                "",
                "00-000",
                "");
        Company defaultSeller = new Company(
                "0000000000",
                "",
                defaultSellerAddress);

        PostalAddress firstBuyerAddress = new PostalAddress(
                "Pokupna",
                "99",
                "9",
                "99-999",
                "Wola Kupczycka");
        Company firstBuyer = new Company(
                "9999999999",
                "U Basi Barbara Nowak",
                firstBuyerAddress);
        PostalAddress secondBuyerAddress = new PostalAddress(
                "Trakt Kupiecki",
                "909",
                "",
                "09-999",
                "Wojsławice");
        Company secondBuyer = new Company(
                "9009998765",
                "Nowak & Nowak",
                secondBuyerAddress);
        PostalAddress thirdBuyerAddress = new PostalAddress(
                "Kardynała Armanda Jeana Richelieu",
                "9",
                "",
                "90-900",
                "Stosy Kościelne");
        Company thirdBuyer = new Company(
                "9990000999",
                "Exitus S.A.",
                thirdBuyerAddress);
        PostalAddress defaultBuyerAddress = new PostalAddress(
                "",
                "",
                "",
                "00-000",
                "");
        Company defaultBuyer = new Company(
                "0000000000",
                "",
                defaultBuyerAddress);

        List<InvoiceEntry> firstInvoiceEntries = new ArrayList<>();
        firstInvoiceEntries.add(new InvoiceEntry("Papier XXL", 5, BigDecimal.valueOf(14.99), VAT_23));
        List<InvoiceEntry> secondInvoiceEntries = new ArrayList<>();
        secondInvoiceEntries.add(new InvoiceEntry("Igła 3\"", 20, BigDecimal.valueOf(0.49), VAT_23));
        secondInvoiceEntries.add(new InvoiceEntry("Nici tapicerskie", 50, BigDecimal.valueOf(4.14), VAT_5));
        secondInvoiceEntries.add(new InvoiceEntry("Skóra niedźwiedzia", 2, BigDecimal.valueOf(1349.0), VAT_0));

        List<InvoiceEntry> thirdInvoiceEntries = new ArrayList<>();
        thirdInvoiceEntries.add(new InvoiceEntry("1256_drewn_opal_kat3_1m3", 20, BigDecimal.valueOf(0.49), VAT_7));
        thirdInvoiceEntries.add(new InvoiceEntry("999_smola_beczk_50l", 12, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("10119_wod_swiecon_250ml", 50, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("transport_max50km", 1, BigDecimal.valueOf(1349.0), VAT_8));

        List<InvoiceEntry> defaultInvoiceEntries = new ArrayList<>();
        defaultInvoiceEntries.add(new InvoiceEntry("", 1, BigDecimal.valueOf(0.01), VAT_0));

        switch (presetId) {
            case 1:
                invoice = new Invoice(
                        LocalDate.of(2019, 12, 10),
                        firstSeller, firstBuyer, firstInvoiceEntries);
                break;
            case 2:
                invoice = new Invoice(
                        LocalDate.of(2020, 3, 12),
                        LocalDate.of(2020, 3, 12),
                        secondSeller, secondBuyer, secondInvoiceEntries);
                break;
            case 3:
                invoice = new Invoice(
                        LocalDate.of(2019, 12, 26),
                        LocalDate.of(2019, 12, 24),
                        thirdSeller, thirdBuyer, thirdInvoiceEntries);
                break;
            default:
                invoice = new Invoice(LocalDate.of(2020, 1, 1),
                        defaultSeller, defaultBuyer, defaultInvoiceEntries);
                break;
        }
        return invoice;
    }

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
            return VAT_8;
        }
        return VAT_ZW;
    }
}
