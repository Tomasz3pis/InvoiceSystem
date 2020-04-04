package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.InvoiceNotfoundExceptions;
import pl.futurecollars.invoice.model.InvoiceProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDataBase implements Database {
    private Database database;
    private HashMap<Long, InvoiceProvider> invoices = new HashMap<>();
    private AtomicLong counter;


    @Override
    public long saveInvoice(InvoiceProvider invoice) {
        invoice.setId(counter.get());
        invoices.put(counter.get(), invoice);
        return counter.getAndIncrement();
    }

    @Override
    public List<InvoiceProvider> getInvoices() {
        return new ArrayList(invoices.values());
    }

    @Override
    public InvoiceProvider getInvoiceById(long id) {
         return invoices.get(id);
    }

    @Override
    public void updateInvoice(InvoiceProvider invoice, long id) {
        invoice = invoices.get(id);
        invoice.setId(counter.getAndIncrement());
    }

    @Override
    public void deleteInvoice(long id) {
        invoices.remove(id);
    }

}
