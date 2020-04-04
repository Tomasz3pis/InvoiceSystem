package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.InvoiceProvider;
import java.util.List;

public interface Database {

    long saveInvoice(InvoiceProvider invoice);

    List<InvoiceProvider> getInvoices();

    InvoiceProvider getInvoiceById(long id);

    void updateInvoice(InvoiceProvider invoice, long id);

    void deleteInvoice(long id);
}
