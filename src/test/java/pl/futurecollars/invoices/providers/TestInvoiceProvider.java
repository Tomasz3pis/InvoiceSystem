package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBestBuy;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBoughtt;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuySome;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyGiftShops;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyPurchasey;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.futureCollars;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountEight;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountFive;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountFour;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountOne;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountSeven;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountSix;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountThree;

import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;

public class TestInvoiceProvider {

    public static Invoice getInvoiceOne() {
        return new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2019, 12, 10))
                .setSaleDate(LocalDate.of(2019, 12, 10))
                .setSeller(companyBuySome())
                .setBuyer(companyBestBuy())
                .setEntries(invoiceEntriesCountOne())
                .build();
    }

    public static Invoice getInvoiceTwo() {
        return new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2020, 3, 12))
                .setSaleDate(LocalDate.of(2020, 3, 12))
                .setSeller(companyBuyMore())
                .setBuyer(companyPurchasey())
                .setEntries(invoiceEntriesCountThree())
                .build();
    }

    public static Invoice getInvoiceThree() {
        return new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2019, 12, 26))
                .setSaleDate(LocalDate.of(2019, 12, 24))
                .setSeller(companyGiftShops())
                .setBuyer(companyBoughtt())
                .setEntries(invoiceEntriesCountFour())
                .build();
    }


    public static Invoice getInvoiceForGrocery() {
        return new Invoice.InvoiceBuilder()
                .setId(6)
                .setIssueDate(LocalDate.of(2020, 01, 15))
                .setSaleDate(LocalDate.of(2020, 01, 24))
                .setSeller(futureCollars())
                .setBuyer(companyBoughtt())
                .setEntries(invoiceEntriesCountFive())
                .build();
    }

    public static Invoice getInvoiceForFruits() {
        return new Invoice.InvoiceBuilder()
                .setId(3)
                .setIssueDate(LocalDate.of(2020, 04, 01))
                .setSaleDate(LocalDate.of(2020, 01, 22))
                .setSeller(futureCollars())
                .setBuyer(companyBoughtt())
                .setEntries(invoiceEntriesCountSix())
                .build();
    }

    public static Invoice getInvoiceForGames() {
        return new Invoice.InvoiceBuilder()
                .setId(2)
                .setIssueDate(LocalDate.of(2020, 04, 01))
                .setSaleDate(LocalDate.of(2020, 01, 22))
                .setSeller(companyBuyMore())
                .setBuyer(futureCollars())
                .setEntries(invoiceEntriesCountSeven())
                .build();
    }

    public static Invoice getInvoiceForSoftWare() {
        return new Invoice.InvoiceBuilder()
                .setId(1)
                .setIssueDate(LocalDate.of(2020, 04, 01))
                .setSaleDate(LocalDate.of(2020, 01, 22))
                .setSeller(companyBuyMore())
                .setBuyer(futureCollars())
                .setEntries(invoiceEntriesCountEight())
                .build();
    }

}
