package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBestBuy;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBoughtt;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuySome;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyGiftShops;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyPurchasey;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountFour;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountOne;
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
}
