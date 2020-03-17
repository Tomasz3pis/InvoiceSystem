package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.Collection;

public interface Database {
    void saveInvoice(Invoice invoice);

    void updateInvoice(long id, Invoice newInvoice);

    void deleteInvoice(long id);

    Invoice getInvoiceById(long id);

    Collection<Invoice> getInvoices();
}
