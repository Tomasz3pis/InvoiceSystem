package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.InvoiceEntry;
import pl.futurecollars.invoices.model.PostalAddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Service
public class TaxCalculatorService {

    @Autowired
    private Database database;
    private final Company futureCollars = new Company("0001", "FutureCollars Sp. z o.o.", new PostalAddress("Koronawirusa", "1", "1", "01-001", "Warszawa"));
    private Invoice invoice;
    private BigDecimal sumOfIncomeVAT;
    private BigDecimal sumOfOutcomeVat;
    private BigDecimal vatValue;
    private BigDecimal income;

    private BigDecimal calculateIncomeForCompany(List<Invoice> invoices, Company company) {
        invoices
                .stream()
                .filter(value -> (invoice.getSeller().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()))
                .forEach(invoice1 -> income = income.add(calculateNetValue(invoice.getEntries())));
        return income;
    }

    private BigDecimal calculateVatForCompany(List<Invoice> invoices, Company company) {
        invoices
                .stream()
                .forEach(invoice1 -> {
                    if (invoice.getSeller().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()) {
                        vatValue = vatValue.add(calculateVatValue(invoice.getEntries()));
                    } else if (invoice.getBuyer().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()) {
                        vatValue = vatValue.add(calculateVatValue(invoice.getEntries()));
                    }
                });
        return vatValue;
    }

    private BigDecimal calculateAllEntriesValuesFromOneInvoice(List<InvoiceEntry> entries, Function<InvoiceEntry, BigDecimal> functionToApply) {
        BigDecimal value = BigDecimal.valueOf(0.0);
        for (InvoiceEntry entry : entries) {
            value.add(functionToApply.apply(entry));
        }
        return value;
    }

    private BigDecimal calculateVatValue(List<InvoiceEntry> entries) {
        return calculateAllEntriesValuesFromOneInvoice(entries, entry -> entry.getNetPrice().multiply(entry.getVat().getRate()));
    }

    private BigDecimal calculateNetValue(List<InvoiceEntry> entries) {
        return calculateAllEntriesValuesFromOneInvoice(entries, InvoiceEntry::getNetPrice);
    }

}
