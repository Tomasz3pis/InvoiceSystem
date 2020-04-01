package pl.futurecollars.invoices.providers;

import static pl.futurecollars.invoices.providers.TestCompanyProvider.buyerWithOffset;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.defaultCompany;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.firstBuyer;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.firstSeller;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.secondBuyer;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.secondSeller;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.sellerWithOffset;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.thirdBuyer;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.thirdSeller;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.defaultInvoiceEntries;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.firstInvoiceEntries;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesWithOffset;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.secondInvoiceEntries;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.thirdInvoiceEntries;

import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;

public class TestInvoiceProvider {

    public static Invoice getInvoice(int presetNumber) {
        Invoice invoice;

        switch (presetNumber) {
            case 1:
                invoice = new Invoice.InvoiceBuilder()
                        .setIssueDate(LocalDate.of(2019, 12, 10))
                        .setSaleDate(LocalDate.of(2019, 12, 10))
                        .setSeller(firstSeller())
                        .setBuyer(firstBuyer())
                        .setEntries(firstInvoiceEntries())
                        .build();
                break;
            case 2:
                invoice = new Invoice.InvoiceBuilder()
                        .setIssueDate(LocalDate.of(2020, 3, 12))
                        .setSaleDate(LocalDate.of(2020, 3, 12))
                        .setSeller(secondSeller())
                        .setBuyer(secondBuyer())
                        .setEntries(secondInvoiceEntries())
                        .build();
                break;
            case 3:
                invoice = new Invoice.InvoiceBuilder()
                        .setIssueDate(LocalDate.of(2019, 12, 26))
                        .setSaleDate(LocalDate.of(2019, 12, 24))
                        .setSeller(thirdSeller())
                        .setBuyer(thirdBuyer())
                        .setEntries(thirdInvoiceEntries())
                        .build();
                break;
            default:
                invoice = new Invoice.InvoiceBuilder()
                        .setIssueDate(LocalDate.of(2020, 1, 1))
                        .setSaleDate(LocalDate.of(2020, 1, 1))
                        .setSeller(defaultCompany())
                        .setBuyer(defaultCompany())
                        .setEntries(defaultInvoiceEntries())
                        .build();
                break;
        }
        return invoice;
    }

    public static Invoice getInvoice(int offset, int numberOfEntries) {

        return new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2020, 1, 2))
                .setSaleDate(LocalDate.of(2020, 1, 1))
                .setSeller(sellerWithOffset(offset))
                .setBuyer(buyerWithOffset(offset))
                .setEntries(invoiceEntriesWithOffset(offset, numberOfEntries))
                .build();
    }
}
