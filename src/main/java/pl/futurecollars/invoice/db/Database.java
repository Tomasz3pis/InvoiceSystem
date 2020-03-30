package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.HashMap;

public interface Database {

    int saveInvoice(Invoice invoice);

    HashMap<Integer, Invoice> getInvoices();

    Invoice getInvoiceById(Integer id);

    void updateInvoice(Invoice invoice, Integer id);

    void deleteInvoice(Integer id);
}
