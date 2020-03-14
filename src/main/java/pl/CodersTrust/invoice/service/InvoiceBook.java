package pl.CodersTrust.invoice.service;


import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.database.InMemoryDatabase;
import pl.CodersTrust.invoice.model.Invoice;


public class InvoiceBook {

    private Database database = new InMemoryDatabase();

    public final void saveInvoiceInDatabase(final Invoice invoice) {
        database.saveInvoice(invoice);
    }

    public final Invoice searchInvoiceById(final long id) {
        return database.getInvoiceById(id);
    }
}
