package pl.futurecollars.invoice.service;

import pl.futurecollars.invoice.db.Database;
import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.model.InvoiceProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceController implements Database {

    private Database database;

    public InvoiceController(InMemoryDataBase inMemoryDataBase) {
        this.database = inMemoryDataBase;
    }

    @Override
    public long saveInvoice(InvoiceProvider invoice) {
        return database.saveInvoice(invoice);
    }

    @Override
    public List<InvoiceProvider> getInvoices() {
        return new ArrayList(database.getInvoices());
    }

    @Override
    public InvoiceProvider getInvoiceById(long id) {
        return database.getInvoiceById(id);
    }

    @Override
    public void updateInvoice(InvoiceProvider invoice, long updatedIndex) {
        database.updateInvoice(invoice, updatedIndex);
    }

    @Override
    public void deleteInvoice(long id) {
        database.deleteInvoice(id);
    }
}
