package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDatabase implements Database {

    private Map<Integer, Invoice> invoices = new HashMap();

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.put(invoice.getId(), invoice);
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        return false;
        //zmiana pozycji w tym samym id
    }

    @Override
    public boolean deleteInvoice(int id) {
        if (invoices.get(id) == null) {
            return false;
        }
        invoices.remove(id);
        return true;
    }

    @Override
    public Invoice getInvoiceById(int id) {
        return invoices.get(id);
    }

    @Override
    public List<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }
}
