package pl.futurecollars.invoice.db;

import org.json.JSONException;
import pl.futurecollars.invoice.model.Invoice;

import java.io.IOException;
import java.util.List;

public interface DataBase {
    void saveInvoice(Invoice invoice) throws IOException;

    List<Invoice> getInvoices() throws IOException;

    Invoice getInvoiceById(String id) throws JSONException, IOException;

    void updateInvoice(Invoice invoice, int updatedIndex);

    void deleteInvoice(String id);
}
