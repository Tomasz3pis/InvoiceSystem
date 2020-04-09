package pl.futurecollars.invoice.database;

import pl.futurecollars.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Database {

    void saveInvoice(Invoice invoice);

    void updateInvoice(long id, Invoice updatedInvoice);

    void deleteInvoice(long id);

    Optional<Invoice> getInvoiceById(long id);

    List<Invoice> getInvoices();

    List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate);
}
