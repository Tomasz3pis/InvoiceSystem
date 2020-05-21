package pl.futurecollars.invoices.database;

import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryDatabase extends AbstractDatabase {

    private Map<Long, Invoice> invoices = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();

    @Override
    public long saveInvoice(Invoice invoice) {
        long id = idCounter.incrementAndGet();
        invoice.setId(id);
        invoices.put(id, invoice);
        return id;
    }

    @Override
    public List<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }

    @Override
    public Optional<Invoice> getInvoiceById(long id) {
        return Optional.ofNullable(invoices.get(id));
    }

    @Override
    public void updateInvoice(long id, Invoice updatedInvoice) {
        updatedInvoice.setId(id);
        Invoice originalInvoice = invoices.replace(id, updatedInvoice);
        if (originalInvoice == null) {
            throw new InvoiceNotFoundException(id);
        }
    }

    @Override
    public void deleteInvoice(long id) {
        Invoice deletedInvoice = invoices.remove(id);
        if (deletedInvoice == null) {
            throw new InvoiceNotFoundException(id);
        }
    }
}
