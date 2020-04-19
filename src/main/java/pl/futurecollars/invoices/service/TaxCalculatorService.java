package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.InvoiceEntry;
import pl.futurecollars.invoices.model.PostalAddress;
import pl.futurecollars.invoices.model.Vat;

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
    private BigDecimal costs;
    private BigDecimal income;

    private BigDecimal calculateAllEntriesValuesFromOneInvoice(List<InvoiceEntry> entries, Function<InvoiceEntry, BigDecimal> functionToApply) {
        BigDecimal taxCost = BigDecimal.valueOf(0.0);
        for (InvoiceEntry entry : entries) {
            taxCost.add(functionToApply.apply(entry));
        }
        return taxCost;
    }

    private BigDecimal calculateVatValue(List<InvoiceEntry> entries) {
        return calculateAllEntriesValuesFromOneInvoice(entries, entry-> entry.getNetPrice().multiply(entry.getVat().getRate()));
    }

    private BigDecimal calculateNetValue(List<InvoiceEntry> entries) {
        return calculateAllEntriesValuesFromOneInvoice(entries, InvoiceEntry::getNetPrice);
    }

    public BigDecimal calculationOfIncomeVat(Company company) {
        database.getInvoices()
                .stream()
                .filter(value -> (invoice.getSeller().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()))
                .forEach(value -> calculateAllEntriesValuesFromOneInvoice(Vat.VAT_23, sumOfIncomeVAT));
        return sumOfIncomeVAT;
    }

    public BigDecimal calculationOfOutcomeVat(Company company) {
        database.getInvoices()
                .stream()
                .filter(value -> (invoice.getBuyer().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()))
                .forEach(value ->calculateAllEntriesValuesFromOneInvoice(Vat.VAT_8, sumOfOutcomeVat));
        return sumOfOutcomeVat;
    }

    public BigDecimal calculationOfCosts() {
        costs = sumOfIncomeVAT.add(sumOfOutcomeVat);
        return costs;
    }

    public BigDecimal calculationIncomeToCost(Company company) {
        database.getInvoices()
                .stream()
                .filter(value -> (invoice.getSeller().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()))
                .forEach(value -> entries.getNetPrice().add(income));
            return income;
    }

}

//TODO 1.  Jedna funkcja validująca consumer lub supplier
//TODO 2.  Funkcja musi przyjmość List of INvoice , iterujemy po invoicash i po entries, Function od Inovice, Company .getbuyer i get seller