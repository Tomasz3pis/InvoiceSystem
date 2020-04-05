package pl.futurecollars.invoice.service;

import pl.futurecollars.invoice.db.Database;
import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.db.NoInvoiceFoundException;
import pl.futurecollars.invoice.model.Invoice;
import java.util.ArrayList;
import java.util.List;

public class InvoiceController implements Database {

    private Database database;

    public InvoiceController(InMemoryDataBase inMemoryDataBase) {
        this.database = inMemoryDataBase;
    }

    @Override
    public void saveInvoice(Invoice invoice) {
        database.saveInvoice(invoice);
    }

    @Override
    public List<Invoice> getInvoices() {
        return new ArrayList(database.getInvoices());
    }

    @Override
    public Invoice getInvoiceById(long id) throws NoInvoiceFoundException {
        if (database.getInvoiceById(id) == null) {
            throw new NoInvoiceFoundException("Invoice not found in database.");
        } else {
            return database.getInvoiceById(id);
        }
    }

    @Override
    public void updateInvoice(Invoice invoice, long id) throws NoInvoiceFoundException {
        if (database.getInvoiceById(id) == null) {
            throw new NoInvoiceFoundException("Invoice not found in database.");
        } else {
            invoice = database.getInvoiceById(id);
        }
    }

    @Override
    public void deleteInvoice(long id) throws NoInvoiceFoundException {
        if (database.getInvoiceById(id) == null) {
            throw new NoInvoiceFoundException("Invoice not found in database.");
        } else {
            database.deleteInvoice(id);
        }
    }
}
