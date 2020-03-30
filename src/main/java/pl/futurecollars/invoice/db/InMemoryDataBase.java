package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;

import java.util.HashMap;

public class InMemoryDataBase implements Database {

    private HashMap<Integer, Invoice> invoices = new HashMap<>();
    private int counter = 0;

    @Override
    public int saveInvoice(Invoice invoice) {
        invoice.setId(counter);
        invoices.put(counter, invoice);
        return counter++;
    }

    @Override
    public HashMap<Integer, Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        return invoices.get(id);
    }

    @Override
    public void updateInvoice(Invoice invoice, Integer id) {
        invoice = invoices.get(id);
        invoice.setId(counter);
        counter++;
    }

    @Override
    public void deleteInvoice(Integer id) {
        invoices.remove(id);
    }

}
