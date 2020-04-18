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
    private BigDecimal incomeToCosts;

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

    public BigDecimal calculationOfIncomeCosts(Company company) {
        database.getInvoices()
                .stream()
                .filter(value -> (invoice.getBuyer().getTaxIdentificationNumber() == company.getTaxIdentificationNumber()))
                .forEach(value ->calculateAllEntriesValuesFromOneInvoice(Vat.VAT_8, sumOfOutcomeVat));
        return sumOfOutcomeVat;
    }

    public BigDecimal calculationOfCosts() {
        incomeToCosts = sumOfIncomeVAT.add(sumOfCosts);
        return incomeToCosts;
    }

    public void calculationOfOutcomeVat(Company company) {
        // NotEnoughKnowledgeAboutTaxes exception
    }

}

//TODO policzyć wartości income VAT wat nalezny 23 moze byc 8 i 5 na towar spozywcze towary lub zwolniony   Outcome VAT  czyli wat naliczony , faktura kosztowa np. faktura za energie placona netto. Doliczanie tak jak w income.   /income / costs  / Ostatnia wartość liczona jest z poprzednich income cost (income - costs)
//TODO napisać 4 funkcji które będą obliczać wartości. Podajemy ID swojej firmy , iterujemy po wszystkich fakturach na koniec liczymy income, outcome , income VAT, costs oraz income cost(income - costs)
//TODO zahardkodować swoją firmę
//TODO policzone wartości zwracamy w Json
//TODO Stworzyć generyczną funkcję, która przyjmuje funkcję którą wsadzamy w jednego if
