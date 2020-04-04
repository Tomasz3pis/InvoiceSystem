package pl.futurecollars.invoice.service;

import pl.futurecollars.invoice.db.Database;
import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.model.InvoiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceController implements Database {

    private Database database;

    public InvoiceController(InMemoryDataBase inMemoryDataBase) {
        this.database = inMemoryDataBase;
    }

    @Override
    public void saveInvoice(InvoiceProvider invoice) {
        database.saveInvoice(invoice);
    }

    @Override
    public List<InvoiceProvider> getInvoices() {
        return new ArrayList(database.getInvoices());
    }

    @Override
    public InvoiceProvider getInvoiceById(long id) throws NoSuchFieldException {
        Optional.ofNullable(database.getInvoiceById(id)).orElseThrow(NoSuchFieldError::new);
        return database.getInvoiceById(id);
    }

    @Override
    public void updateInvoice(InvoiceProvider invoice, long id) throws NoSuchFieldException {
        Optional.ofNullable(database.getInvoiceById(id)).orElseThrow(NoSuchFieldError::new);
        invoice = database.getInvoiceById(id);
    }

    @Override
    public void deleteInvoice(long id) {
        database.deleteInvoice(id);
    }
}
