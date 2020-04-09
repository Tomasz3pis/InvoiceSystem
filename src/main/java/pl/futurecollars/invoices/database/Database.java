package pl.futurecollars.invoices.database;

import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Database {

    long saveInvoice(Invoice invoice);

    List<Invoice> getInvoices();

    List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate);

    Optional<Invoice> getInvoiceById(long id);

    void updateInvoice(long id, Invoice updatedInvoice);

    void deleteInvoice(long id);
}
