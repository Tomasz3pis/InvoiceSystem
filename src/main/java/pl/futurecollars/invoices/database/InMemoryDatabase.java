package pl.futurecollars.invoices.database;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDatabase implements Database {

    private Map<Long, Invoice> invoices = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();

    @Override
    public void saveInvoice(Invoice invoice) {
        checkForNull(invoice, "invoice");
        Long id = generateId();
        invoice.setId(id);
        invoices.put(id, invoice);
    }

    @Override
    public Map<Long, Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        checkForNull(id, "id");
        return Optional.ofNullable(invoices.get(id));
    }

    @Override
    public void updateInvoice(Invoice updatedInvoice) {
        checkForNull(updatedInvoice, "updatedInvoice");
        Long updatedInvoiceId = updatedInvoice.getId();
        Invoice originalInvoice = invoices.replace(updatedInvoiceId, updatedInvoice);
        if (originalInvoice == null) {
            throw new InvoiceNotFoundException(
                    "Provided updatedInvoice does not exist in database. "
                            + "Invoice id: " + updatedInvoiceId + " not found.");
        }
    }

    @Override
    public void deleteInvoice(Long id) {
        checkForNull(id, "id");
        Invoice deletedInvoice = invoices.remove(id);
        if (deletedInvoice == null) {
            throw new IllegalArgumentException(
                    "Provided id: "
                            + id
                            + " not found in Database.");
        }
    }

    private Long generateId() {
        return idCounter.incrementAndGet();
    }
}
