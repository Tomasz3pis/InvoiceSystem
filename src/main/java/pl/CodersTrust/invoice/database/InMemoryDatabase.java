package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {

    private Map<Long, Invoice> invoices = new HashMap();

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.put(invoice.getId(), invoice);
    }

    // jak zaimpelentowac modyfikacje zmiany. (konstruktor) Skad wziac dane do nowej faktury?!
    @Override
    public boolean updateInvoice(Invoice invoice, Invoice newInvoice) {
        if (invoices.get(invoice.getId()) == null) {
            return false;
        }
        invoices.put(invoice.getId(), newInvoice);
        return true;
    }

    @Override
    public boolean deleteInvoice(Invoice invoice) {
        if (invoices.get(invoice.getId()) == null) {
            return false;
        }
        invoices.remove(invoice.getId());
        return true;
    }

    @Override
    public Invoice getInvoiceById(long id) {
        return invoices.get(id);
    }

    @Override
    public Collection<Invoice> getInvoices() {
        return invoices.values();
    }
}
