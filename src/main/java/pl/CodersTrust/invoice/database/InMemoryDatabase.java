package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {

    private Map<Long, Invoice> invoices = new HashMap();

    @Override
    public final void saveInvoice(final Invoice invoice) {
        invoices.put(invoice.getId(), invoice);
    }

    @Override
    public final void updateInvoice(final Invoice invoice, final Invoice newInvoice) {
        invoices.put(invoice.getId(), newInvoice);
    }

    @Override
    public final void deleteInvoice(final Invoice invoice) {
        invoices.remove(invoice.getId());
    }

    @Override
    public final Invoice getInvoiceById(final long id) {
        return invoices.get(id);
    }

    @Override
    public final Collection<Invoice> getInvoices() {
        return invoices.values();
    }
}
