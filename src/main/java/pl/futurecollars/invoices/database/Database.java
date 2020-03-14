package pl.futurecollars.invoices.database;

import pl.futurecollars.invoices.model.Invoice;

import java.util.List;

public interface Database {

    void saveInvoice(Invoice invoice);

    List<Invoice> getInvoices();

    Invoice getInvoiceById(String id);

    void updateInvoice(Invoice updatedInvoice);

    void deleteInvoice(String id);

    void addId(String id);

    List<String> getIdNumbers();

    String getLastId();

    boolean containsId(String id);
}
