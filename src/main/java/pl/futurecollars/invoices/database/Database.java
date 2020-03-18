package pl.futurecollars.invoices.database;

import pl.futurecollars.invoices.model.Invoice;

import java.util.Map;
import java.util.Optional;

public interface Database {

    void saveInvoice(Invoice invoice);

    Map<String, Invoice> getInvoices();

    Optional<Invoice> getInvoiceById(String id);

    void updateInvoice(Invoice updatedInvoice);

    void deleteInvoice(String id);
}
