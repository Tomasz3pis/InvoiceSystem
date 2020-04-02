package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.InvoiceProvider;

import java.util.HashMap;

public class InMemoryDataBase implements Database {

    private HashMap<Integer, InvoiceProvider> invoices = new HashMap<>();
    private int counter = 0;

    @Override
    public int saveInvoice(InvoiceProvider invoice) {
        invoice.setId(counter);
        invoices.put(counter, invoice);
        return counter++;
    }

    @Override
    public HashMap<Integer, InvoiceProvider> getInvoices() {
        return invoices;
    }

    @Override
    public InvoiceProvider getInvoiceById(Integer id) {
        return invoices.get(id);
    }

    @Override
    public void updateInvoice(InvoiceProvider invoice, Integer id) {
        invoice = invoices.get(id);
        invoice.setId(counter);
        counter++;
    }

    @Override
    public void deleteInvoice(Integer id) {
        invoices.remove(id);
    }

}
