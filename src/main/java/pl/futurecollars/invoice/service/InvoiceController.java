package pl.futurecollars.invoice.service;

import pl.futurecollars.invoice.db.Database;
import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.model.InvoiceProvider;
import java.util.HashMap;

public class InvoiceController implements Database {

    private InMemoryDataBase inMemoryDataBase;

    public InvoiceController(InMemoryDataBase inMemoryDataBase) {
        this.inMemoryDataBase = inMemoryDataBase;
    }

    @Override
    public int saveInvoice(InvoiceProvider invoice) {
        return inMemoryDataBase.saveInvoice(invoice);
    }

    @Override
    public HashMap<Integer, InvoiceProvider> getInvoices() {
        return inMemoryDataBase.getInvoices();
    }

    @Override
    public InvoiceProvider getInvoiceById(Integer id) {
        return inMemoryDataBase.getInvoiceById(id);
    }

    @Override
    public void updateInvoice(InvoiceProvider invoice, Integer updatedIndex) {
        inMemoryDataBase.updateInvoice(invoice, updatedIndex);
    }

    @Override
    public void deleteInvoice(Integer id) {
        inMemoryDataBase.deleteInvoice(id);
    }
}
