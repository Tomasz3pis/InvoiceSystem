package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.model.Vat.VAT_0;
import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_5;
import static pl.futurecollars.invoices.model.Vat.VAT_7;
import static pl.futurecollars.invoices.model.Vat.VAT_8;
import static pl.futurecollars.invoices.model.Vat.VAT_ZW;

import pl.futurecollars.invoices.model.InvoiceEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestEntriesProvider {

    public static List<InvoiceEntry> invoiceEntriesCountOne() {
        List<InvoiceEntry> firstInvoiceEntries = new ArrayList<>();
        firstInvoiceEntries.add(new InvoiceEntry("One and only product", 5, BigDecimal.valueOf(14.99), VAT_23));
        return firstInvoiceEntries;
    }

    public static List<InvoiceEntry> invoiceEntriesCountThree() {
        List<InvoiceEntry> secondInvoiceEntries = new ArrayList<>();
        secondInvoiceEntries.add(new InvoiceEntry("first product", 20, BigDecimal.valueOf(0.49), VAT_23));
        secondInvoiceEntries.add(new InvoiceEntry("second product", 50, BigDecimal.valueOf(4.14), VAT_5));
        secondInvoiceEntries.add(new InvoiceEntry("third product", 2, BigDecimal.valueOf(1349.0), VAT_0));
        return secondInvoiceEntries;
    }

    public static List<InvoiceEntry> invoiceEntriesCountFour() {
        List<InvoiceEntry> thirdInvoiceEntries = new ArrayList<>();
        thirdInvoiceEntries.add(new InvoiceEntry("product 1.", 20, BigDecimal.valueOf(0.49), VAT_7));
        thirdInvoiceEntries.add(new InvoiceEntry("product 2.", 12, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("product 3.", 50, BigDecimal.valueOf(4.14), VAT_ZW));
        thirdInvoiceEntries.add(new InvoiceEntry("product 4.", 1, BigDecimal.valueOf(1349.0), VAT_8));
        return thirdInvoiceEntries;
    }

    public static List<InvoiceEntry> invoiceEntriesCountFive() {
        List<InvoiceEntry> fourthInvoiceEntries = new ArrayList<>();
        fourthInvoiceEntries.add(new InvoiceEntry("Coco ", 211, BigDecimal.valueOf(0.49), VAT_7));
        fourthInvoiceEntries.add(new InvoiceEntry("Vaila ice", 12, BigDecimal.valueOf(4.14), VAT_ZW));
        fourthInvoiceEntries.add(new InvoiceEntry("Milk", 20, BigDecimal.valueOf(4.14), VAT_ZW));
        fourthInvoiceEntries.add(new InvoiceEntry("Chips", 25, BigDecimal.valueOf(1349.0), VAT_8));
        return fourthInvoiceEntries;
    }

    public static List<InvoiceEntry> invoiceEntriesCountSix() {
        List<InvoiceEntry> fiveInvoiceEntries = new ArrayList<>();
        fiveInvoiceEntries.add(new InvoiceEntry("Watermalons", 1, BigDecimal.valueOf(100.0), VAT_23));
        fiveInvoiceEntries.add(new InvoiceEntry("Apple", 1, BigDecimal.valueOf(10), VAT_0));
        fiveInvoiceEntries.add(new InvoiceEntry("Chili Pepper", 1, BigDecimal.valueOf(100), VAT_5));
        return fiveInvoiceEntries;
    }

    public static List<InvoiceEntry> invoiceEntriesCountSeven() {
        List<InvoiceEntry> sevenInvoiceEntries = new ArrayList<>();
        sevenInvoiceEntries.add(new InvoiceEntry("RedDead Redemption", 1, BigDecimal.valueOf(250.9), VAT_23));
        sevenInvoiceEntries.add(new InvoiceEntry("Doom Eternal", 666, BigDecimal.valueOf(666.6), VAT_0));
        sevenInvoiceEntries.add(new InvoiceEntry("Forza", 5, BigDecimal.valueOf(99.9), VAT_5));
        return sevenInvoiceEntries;
    }

    public static List<InvoiceEntry> invoiceEntriesCountEight() {
        List<InvoiceEntry> eightInvoiceEntries = new ArrayList<>();
        eightInvoiceEntries.add(new InvoiceEntry("Windows 10 Home Edition", 5, BigDecimal.valueOf(1.1), VAT_23));
        eightInvoiceEntries.add(new InvoiceEntry("Linux - Ubuntu", 1000, BigDecimal.valueOf(500.0), VAT_0));
        eightInvoiceEntries.add(new InvoiceEntry("Linux - Mint", 5, BigDecimal.valueOf(150.0), VAT_5));
        return eightInvoiceEntries;
    }

}
