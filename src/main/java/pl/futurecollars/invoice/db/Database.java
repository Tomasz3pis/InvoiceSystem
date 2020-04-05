package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.List;

public interface Database {

    void saveInvoice(Invoice invoice);

    List<Invoice> getInvoices();

    Invoice getInvoiceById(long id) throws NoInvoiceFoundException;

    void updateInvoice(Invoice invoice, long id) throws NoInvoiceFoundException;

    void deleteInvoice(long id) throws NoInvoiceFoundException;
}
