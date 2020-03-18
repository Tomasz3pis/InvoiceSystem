package pl.futurecollars.invoices.database;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.helpers.CheckIdFormat.checkIdFormat;

import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDatabase implements Database {

    private Map<String, Invoice> invoices = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();

    @Override
    public void saveInvoice(Invoice invoice) {
        checkForNull(invoice, "invoice");
        String id = generateId(invoice.getSaleDate());
        invoice.setId(id);
        invoices.put(id, invoice);
    }

    @Override
    public Map<String, Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public Optional<Invoice> getInvoiceById(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        return Optional.ofNullable(invoices.get(id));
    }

    @Override
    public void updateInvoice(Invoice updatedInvoice) {
        checkForNull(updatedInvoice, "updatedInvoice");
        String updatedInvoiceId = updatedInvoice.getId();
        checkIdFormat(updatedInvoiceId);
        Invoice originalInvoice = invoices
                .replace(updatedInvoiceId, updatedInvoice);
        if (originalInvoice == null) {
            throw new IllegalArgumentException(
                    "Provided updatedInvoice does not exist in database. "
                            + "Invoice id: " + updatedInvoiceId + " not found."
            );
        }
    }

    @Override
    public void deleteInvoice(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        Invoice deletedInvoice = invoices.remove(id);
        if (deletedInvoice == null) {
            throw new IllegalArgumentException(
                    "Provided id: "
                            + id
                            + " not found in Database.");
        }
    }

    private String generateId(LocalDate saleDate) {
        checkForNull(saleDate, "saleDate");
        return String.format("%04d%02d%02d%1s%04d",
                saleDate.getYear(),
                saleDate.getMonthValue(),
                saleDate.getDayOfMonth(),
                "_",
                idCounter.incrementAndGet());
    }
}
