package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;

import java.util.List;

public interface DataBase {
    void saveInvoice(Invoice invoice);

    List<Invoice> getInvoices();
}
