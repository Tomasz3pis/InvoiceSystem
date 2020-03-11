package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryDatabase implements Database {

    private Map<UUID, Invoice> invoices = new HashMap();

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.put(invoice.getId(), invoice);
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        return false;
    }

    @Override
    public boolean deleteInvoice(UUID id) {
        if (invoices.get(id) == null) {
            return false;
        }
        invoices.remove(id);
        return true;
    }

    @Override
    public Invoice getInvoiceById(UUID id) {
        return invoices.get(id);
    }

    @Override
    public List<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }
}
