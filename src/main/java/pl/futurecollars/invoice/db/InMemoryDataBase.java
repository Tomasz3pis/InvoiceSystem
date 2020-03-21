package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase implements Database {
    private List<Invoice> invoices = new ArrayList<>();

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(String id) {
        for (Invoice invoice : invoices) {
            if (invoice.getId().equals(id)) {
                return invoice;
            }
        }
        throw new IllegalArgumentException(
                "Invoice id: " + id
                + " not found in Database.");
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
            }
            throw new IllegalArgumentException(
                    "" + "Invoice id: " + id
                            + " not found in Database.");
        }
    }

}
