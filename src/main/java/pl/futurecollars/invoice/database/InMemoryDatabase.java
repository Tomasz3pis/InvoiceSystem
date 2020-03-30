package pl.futurecollars.invoice.database;

import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDatabase implements Database {

    private static AtomicLong lastUsedId = new AtomicLong();
    private Map<Long, Invoice> invoices = new HashMap();

    @Override
    public final void saveInvoice(final Invoice invoice) {
        invoice.setId(getLastUsedId().incrementAndGet());
        invoices.put(invoice.getId(), invoice);
    }

    @Override
    public final void updateInvoice(final long id, final Invoice updatedInvoice) {
        if (!invoices.containsKey(id)) {
            throw new InvoiceNotFoundException(id);
        }
        updatedInvoice.setId(id);
        invoices.put(id, updatedInvoice);
    }

    @Override
    public final void deleteInvoice(final long id) {
        if (!invoices.containsKey(id)) {
            throw new InvoiceNotFoundException(id);
        }
        invoices.remove(id);
    }

    @Override
    public final Invoice getInvoiceById(final long id) {
        if (!invoices.containsKey(id)) {
            Optional<Invoice> empty = Optional.empty();
            return empty.get();
        }
        return invoices.get(id);
    }

    @Override
    public final Collection<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }

    private static AtomicLong getLastUsedId() {
        return lastUsedId;
    }
}
