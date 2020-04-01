package pl.futurecollars.invoice.database;

import pl.futurecollars.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.Collection;

public interface Database {

    void saveInvoice(Invoice invoice);

    void updateInvoice(long id, Invoice newInvoice);

    void deleteInvoice(long id);

    Invoice getInvoiceById(long id);

    Collection<Invoice> getInvoices();

    Collection<Invoice> getInvoices(LocalDate startDate, LocalDate endDate);
}
