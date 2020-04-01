package pl.futurecollars.invoices.database;

import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryDatabase implements Database {

    private Map<Long, Invoice> invoices = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();

    @Override
    public long saveInvoice(Invoice invoice) {
        long id = generateId();
        invoice.setId(id);
        invoices.put(id, invoice);
        return id;
    }

    @Override
    public List<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }

    @Override
    public List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return getInvoices();
        }
        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.MAX;
        }
        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate = endDate;
        return getInvoices().stream()
                .filter(invoice -> invoice.getIssueDate().isAfter(finalStartDate)
                        && invoice.getIssueDate().isBefore(finalEndDate))
                .collect(Collectors.toList());
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
            throw new InvoiceNotFoundException(
                    "Provided updatedInvoice does not exist in database. "
                            + "Invoice id: " + id + " not found.");
        }
    }

    @Override
    public void deleteInvoice(long id) {
        Invoice deletedInvoice = invoices.remove(id);
        if (deletedInvoice == null) {
            throw new InvoiceNotFoundException(
                    "Provided id: "
                            + id
                            + " not found in Database.");
        }
    }

    private long generateId() {
        return idCounter.incrementAndGet();
    }
}
