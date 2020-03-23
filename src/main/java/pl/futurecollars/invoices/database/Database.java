package pl.futurecollars.invoices.database;

import pl.futurecollars.invoices.model.Invoice;

import java.util.Map;
import java.util.Optional;

public interface Database {

    void saveInvoice(Invoice invoice);

    Map<Long, Invoice> getInvoices();

    Optional<Invoice> getInvoiceById(Long id);

    void updateInvoice(Invoice updatedInvoice);

    void deleteInvoice(Long id);
}
