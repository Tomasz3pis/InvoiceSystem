package pl.CodersTrust.invoice.database;

import pl.CodersTrust.invoice.model.Invoice;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDatabase implements Database {

    public static final AtomicLong idCount = new AtomicLong();
    private Map<Long, Invoice> invoices = new HashMap();

    @Override
    public final void saveInvoice(final Invoice invoice) {
        invoice.setId(idCount.incrementAndGet());
        invoices.put(invoice.getId(), invoice);
    }

    @Override
    public final void updateInvoice(final long id, final Invoice newInvoice) {
        newInvoice.setId(id);
        invoices.put(id, newInvoice);
    }

    @Override
    public final void deleteInvoice(final long id) {
        invoices.remove(id);
    }

    @Override
    public final Invoice getInvoiceById(final long id) {
        return invoices.get(id);
    }

    @Override
    public final Collection<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }
}
