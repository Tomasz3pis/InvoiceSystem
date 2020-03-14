package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.Collection;

public interface Database {
    void saveInvoice(Invoice invoice);

    void updateInvoice(Invoice invoice, Invoice newInvoice);

    void deleteInvoice(Invoice invoice);

    Invoice getInvoiceById(long id);

    Collection<Invoice> getInvoices();
}
