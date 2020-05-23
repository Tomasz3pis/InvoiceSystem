package pl.futurecollars.invoices.service;

import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.InvoiceEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Service
public class TaxCalculatorService {

      private BigDecimal calculateAllEntriesValuesFromOneInvoice(String taxIdentificationNumber,
                                                               List<Invoice> invoices,
                                                               Function<Invoice, Company> functionInvoiceCompanyChecker,
                                                               Function<InvoiceEntry,
                                                                       BigDecimal> functionSumOfValueFromAllEnries) {
        BigDecimal value = BigDecimal.valueOf(0);
        for (Invoice invoice : invoices) {
            if (taxIdentificationNumber.equals(functionInvoiceCompanyChecker
                    .apply(invoice).getTaxIdentificationNumber())) {
                for (InvoiceEntry entry : invoice.getEntries()) {
                    value = value.add(functionSumOfValueFromAllEnries.apply(entry));
                }
            }
        }
        return value;
    }

      BigDecimal calculateIncomeVat(List<Invoice> invoices, String taxIdentificationNumber) {
        return calculateAllEntriesValuesFromOneInvoice(taxIdentificationNumber, invoices,
                Invoice::getSeller,
                entry -> entry.getVat().getRate().multiply(entry.getNetPrice()));
    }

       BigDecimal calculateOutcomeVat(List<Invoice> invoices, String taxIdentificationNumber) {
        return calculateAllEntriesValuesFromOneInvoice(taxIdentificationNumber, invoices,
                Invoice::getBuyer,
                entry -> entry.getVat().getRate().multiply(entry.getNetPrice()));
    }

      BigDecimal calculateIncome(List<Invoice> invoices, String taxIdentificationNumber) {
        return calculateAllEntriesValuesFromOneInvoice(taxIdentificationNumber, invoices,
                Invoice::getSeller, entry -> entry.getNetPrice());
    }

       BigDecimal calculateCosts(List<Invoice> invoices, String taxIdentificationNumber) {
        return calculateIncomeVat(invoices, taxIdentificationNumber)
                .add(calculateOutcomeVat(invoices, taxIdentificationNumber));
    }

       BigDecimal calculateIncomeToCosts(List<Invoice> invoices, String taxIdentificationNumber) {
        return calculateIncome(invoices, taxIdentificationNumber)
                .subtract(calculateCosts(invoices, taxIdentificationNumber));
    }

}
