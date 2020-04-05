package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDataBase implements Database {
    private Database database;
    private HashMap<Long, Invoice> invoices = new HashMap<>();
    private AtomicLong counter;

    @Override
    public void saveInvoice(Invoice invoice) {
        invoice.setId(counter.get());
        invoices.put(counter.get(), invoice);
        counter.getAndIncrement();
    }

    @Override
    public List<Invoice> getInvoices() {
        return new ArrayList(invoices.values());
    }

    @Override
    public Invoice getInvoiceById(long id) throws NoInvoiceFoundException {
        if (invoices.get(id) == null) {
            throw new NoInvoiceFoundException("Invoice not found in database.");
        } else {
            return invoices.get(id);
        }
    }

    @Override
    public void updateInvoice(Invoice invoice, long id) throws NoInvoiceFoundException {
        if (invoices.get(id) == null) {
            throw new NoInvoiceFoundException("Invoice not found in database.");
        } else {
            invoice = invoices.get(id);
            invoice.setId(counter.getAndIncrement());
        }
    }

    @Override
    public void deleteInvoice(long id) throws NoInvoiceFoundException, NoInvoiceFoundException {
        if (invoices.get(id) == null) {
            throw new NoInvoiceFoundException("Invoice not found in database.");
        } else {
            invoices.remove(id);
        }
    }

}
