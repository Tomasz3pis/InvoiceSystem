package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.InvoiceProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDataBase implements Database {
    private Database database;
    private HashMap<Long, InvoiceProvider> invoices = new HashMap<>();
    private AtomicLong counter;


    @Override
    public void saveInvoice(InvoiceProvider invoice) {
        invoice.setId(counter.get());
        invoices.put(counter.get(), invoice);
        counter.getAndIncrement();
    }

    @Override
    public List<InvoiceProvider> getInvoices() {
        return new ArrayList(invoices.values());
    }

    @Override
    public InvoiceProvider getInvoiceById(long id) throws NoSuchFieldException {
        Optional.ofNullable(invoices.get(id)).orElseThrow(NoSuchFieldException::new);
        return invoices.get(id);
    }

    @Override
    public void updateInvoice(InvoiceProvider invoice, long id) throws NoSuchFieldException {
        Optional.ofNullable(invoices.get(id)).orElseThrow(NoSuchFieldException::new);
        invoice = invoices.get(id);
        invoice.setId(counter.getAndIncrement());
    }

    @Override
    public void deleteInvoice(long id) {
        invoices.remove(id);
    }

}
