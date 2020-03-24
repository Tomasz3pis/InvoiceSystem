package pl.coderstrust.invoice.service;

import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.model.Invoice;
import java.util.Collection;

public class InvoiceService {

    private Database database;

    public InvoiceService(Database database) {
        this.database = database;
    }

    public final void saveInvoiceInDatabase(final Invoice invoice) {
        database.saveInvoice(invoice);
    }

    public final Invoice searchInvoiceById(final long id) {
        return database.getInvoiceById(id);
    }

    public final Collection<Invoice> getAllInvoices() {
        return database.getInvoices();
    }

    public final void updateInvoice(final long id, final Invoice newInvoice) {
        database.updateInvoice(id, newInvoice);
    }

    public final void deleteInvoice(final long id) {
        database.deleteInvoice(id);
    }
}
