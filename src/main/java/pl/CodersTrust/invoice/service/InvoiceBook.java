package pl.CodersTrust.invoice.service;


import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.model.Invoice;


public class InvoiceBook {

    private Database database;

    public InvoiceBook(Database database) {
        this.database = database;
    }

    public final void saveInvoiceInDatabase(final Invoice invoice) {
        database.saveInvoice(invoice);
    }

    public final Invoice searchInvoiceById(final long id) {
        return database.getInvoiceById(id);
    }

    public final void updateInvoice(final long id, final Invoice newInvoice) {
        database.updateInvoice(id, newInvoice);
    }

    public final void deleteInvoice(final long id) {
        database.deleteInvoice(id);
    }
}
