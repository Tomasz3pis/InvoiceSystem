package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.List;

public interface Database {
    void saveInvoice(Invoice invoice);

    List<Invoice> getInvoices();

    Invoice getInvoiceById(String id);

    void updateInvoice(Invoice invoice, int updatedIndex);

    void deleteInvoice(String id);
}
