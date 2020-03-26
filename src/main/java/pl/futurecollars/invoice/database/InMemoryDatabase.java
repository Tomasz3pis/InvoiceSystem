package pl.futurecollars.invoice.database;

import pl.futurecollars.invoice.NotExistingInvoiceException;
import pl.futurecollars.invoice.model.Invoice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDatabase implements Database {

    private static AtomicLong lastUsedId = new AtomicLong();
    private Map<Long, Invoice> invoices = new HashMap();

    @Override
    public final void saveInvoice(final Invoice invoice) {
        invoice.setId(lastUsedId.incrementAndGet());
        invoices.put(invoice.getId(), invoice);
    }

    @Override
    public final void updateInvoice(final long id, final Invoice updatedInvoice) {
        if (invoices.get(id) == null) {
            throw new NotExistingInvoiceException(
                    "Invoice with given id do not exist"
            );
        }
        updatedInvoice.setId(id);
        invoices.put(id, updatedInvoice);
    }

    @Override
    public final void deleteInvoice(final long id) {
        if (invoices.get(id) == null) {
            throw new NotExistingInvoiceException(
                    "Invoice not exist"
            );
        }
        invoices.remove(id);
    }

    @Override
    public final Invoice getInvoiceById(final long id) {
        if (invoices.get(id) == null) {
            throw new NotExistingInvoiceException(
                    "Invoice do not exist"
            );
        }
        return invoices.get(id);
    }

    @Override
    public final Collection<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }

    public static AtomicLong getLastUsedId() {
        return lastUsedId;
    }
}
