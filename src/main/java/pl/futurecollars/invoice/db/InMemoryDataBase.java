package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase implements DataBase {
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

        throw new IllegalArgumentException("Passed id: " + id + " not found in Database");
    }

    @Override
    public void updateInvoice(Invoice invoice, String Id) {

    }
}
