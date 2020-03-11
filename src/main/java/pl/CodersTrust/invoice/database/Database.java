package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.List;
import java.util.UUID;

public interface Database {
    void saveInvoice(Invoice invoice);
    boolean updateInvoice(Invoice invoice);
    boolean deleteInvoice(UUID id);
    Invoice getInvoiceById(UUID id);
    List<Invoice> getInvoices();
}
