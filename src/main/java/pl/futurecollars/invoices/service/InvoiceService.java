package pl.futurecollars.invoices.service;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.util.Map;
import java.util.Optional;

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

    public Map<Long, Invoice> getInvoices() {
        return database.getInvoices();
    }

    public Optional<Invoice> getInvoice(Long id) {
        checkForNull(id, "id");
        return database.getInvoiceById(id);
    }

    public void updateInvoice(Invoice updatedInvoice) {
        checkForNull(updatedInvoice, "updatedInvoice");
        database.updateInvoice(updatedInvoice);
    }

    public void deleteInvoice(Long id) {
        checkForNull(id, "id");
        database.deleteInvoice(id);
    }
}
