package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.InvoiceProvider;

import java.nio.file.NoSuchFileException;
import java.util.List;

public interface Database {

    void saveInvoice(InvoiceProvider invoice);

    List<InvoiceProvider> getInvoices();

    InvoiceProvider getInvoiceById(long id) throws NoSuchFieldException;

    void updateInvoice(InvoiceProvider invoice, long id) throws NoSuchFieldException;

    void deleteInvoice(long id);
}
