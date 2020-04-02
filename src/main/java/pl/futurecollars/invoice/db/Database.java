package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.InvoiceProvider;
import java.util.HashMap;

public interface Database {

    int saveInvoice(InvoiceProvider invoice);

    HashMap<Integer, InvoiceProvider> getInvoices();

    InvoiceProvider getInvoiceById(Integer id);

    void updateInvoice(InvoiceProvider invoice, Integer id);

    void deleteInvoice(Integer id);
}
