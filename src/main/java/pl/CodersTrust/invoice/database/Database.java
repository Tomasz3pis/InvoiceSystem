package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.List;

public interface Database {
    void saveInvoice(Invoice invoice);
    boolean updateInvoice(Invoice invoice);
    boolean deleteInvoice(int id);
    Invoice getInvoiceById(int id);
    List<Invoice> getInvoices();
}
