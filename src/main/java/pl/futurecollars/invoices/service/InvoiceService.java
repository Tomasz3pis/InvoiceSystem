package pl.futurecollars.invoices.service;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.helpers.CheckIdFormat.checkIdFormat;

import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.util.List;

public class InvoiceService {

    private Database database;

    public InvoiceService(Database database) {
        checkForNull(database, "database");
        this.database = database;
    }

    public void saveInvoice(Invoice invoice) {
        checkForNull(invoice, "invoice");
        database.saveInvoice(invoice);
    }

    public List<Invoice> getInvoices() {
        return database.getInvoices();
    }

    public Invoice getInvoice(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        return database.getInvoiceById(id);
    }

    public void updateInvoice(Invoice updatedInvoice) {
        checkForNull(updatedInvoice, "updatedInvoice");
        database.updateInvoice(updatedInvoice);
    }

    public void deleteInvoice(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        database.deleteInvoice(id);
    }
}
