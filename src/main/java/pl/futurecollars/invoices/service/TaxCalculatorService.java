package pl.futurecollars.invoices.service;

import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.InvoiceEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Service
public class TaxCalculatorService {

    private Invoice invoice;
    private BigDecimal vatValue;
    private BigDecimal income;
    private BigDecimal incomeToCosts;

    private BigDecimal calculateIncomeForCompany(List<Invoice> invoices, String taxIdentificationNumber, Function<List<InvoiceEntry>, BigDecimal> functionToApply) {
        invoices.stream()
                .filter(value -> (invoice.getSeller().getTaxIdentificationNumber().equals(taxIdentificationNumber)))
                .forEach(invoice1 -> income.add(functionToApply.apply(invoice.getEntries())));
        return income;
    }

    private BigDecimal calculateVatForCompany(List<Invoice> invoices, String taxIdentificationNumber, Function<List<InvoiceEntry>, BigDecimal> functionToApply) {
        invoices.stream()
                .forEach(invoice1 -> {
                    if (invoice.getSeller().getTaxIdentificationNumber().equals(taxIdentificationNumber)) {
                        vatValue.add(functionToApply.apply(invoice.getEntries()));
                    } else if (invoice.getBuyer().getTaxIdentificationNumber().equals(taxIdentificationNumber)) {
                        vatValue.add(functionToApply.apply(invoice.getEntries()));
                    }
                });
        return vatValue;
    }

    private BigDecimal calculateIncomeToCosts(List<Invoice> invoices, String taxIdentificationNumber, Function<List<InvoiceEntry>, BigDecimal> functionToApply) {
        incomeToCosts = calculateIncomeForCompany(invoices, taxIdentificationNumber, functionToApply).subtract(calculateVatForCompany(invoices, taxIdentificationNumber, functionToApply));
        return incomeToCosts;
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
