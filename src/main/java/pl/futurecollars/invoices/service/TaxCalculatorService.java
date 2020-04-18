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
    private InvoiceEntry entries;

    public BigDecimal calculateAllEntriesValuesFromOneInvoice(Vat vat, BigDecimal taxCost) {
        BigDecimal temporaryValue = BigDecimal.valueOf(0.0);
        for (InvoiceEntry entries : invoice.getEntries()) {
            temporaryValue = entries.getNetPrice().multiply(vat.getRate());
            taxCost.add(temporaryValue);
        }
        return taxCost;
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
