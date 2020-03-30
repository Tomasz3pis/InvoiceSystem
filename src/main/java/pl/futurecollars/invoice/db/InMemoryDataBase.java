/*
package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.HashMap;
import java.util.Optional;

public class InMemoryDataBase implements Database {
    private Invoice invoice;
    private HashMap<String, Invoice> invoices = new HashMap<>();

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public HashMap<String, Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(String id) {
        invoice = invoices.get(id);
        return invoice;
    }

    @Override
    public void updateInvoice(Invoice invoice, int updatedIndex) {
        if (updatedIndex > invoices.size()) {
            throw new IndexOutOfBoundsException(
                    "List of invoices is between : 0 to  "
                            + invoices.size()
                            + " Please pass correct index.");
        }
        invoices.set(updatedIndex, invoice);

    }

    @Override
    public void deleteInvoice(String id) {
        for (Invoice invoice : invoices) {
            if (invoice.getId().equals(id)) {
                invoices.remove(id);
            } else {
                Optional.empty();
            }
        }
    }

}
*/
