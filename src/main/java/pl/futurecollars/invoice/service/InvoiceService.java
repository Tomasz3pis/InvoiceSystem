package pl.futurecollars.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.futurecollars.invoice.database.Database;
import pl.futurecollars.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class InvoiceService {

    @Autowired
    private Database database;

    public InvoiceService(Database database) {
        this.database = database;
    }

    public final void saveInvoice(final Invoice invoice) {
        database.saveInvoice(invoice);
    }

    public final Invoice findInvoiceById(final long id) {
        return database.getInvoiceById(id);
    }

    public final Collection<Invoice> getAllInvoices() {
        return database.getInvoices();
    }

    public final Collection<Invoice> getInvoices(LocalDate startDate, LocalDate endDate) {
        return database.getInvoices(startDate, endDate);
    }

    public final void updateInvoice(final long id, final Invoice newInvoice) {
        database.updateInvoice(id, newInvoice);
    }

    public final void deleteInvoice(final long id) {
        database.deleteInvoice(id);
    }
}
