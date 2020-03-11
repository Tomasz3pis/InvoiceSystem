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
