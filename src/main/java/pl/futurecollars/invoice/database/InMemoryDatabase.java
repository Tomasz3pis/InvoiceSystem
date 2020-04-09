package pl.futurecollars.invoice.database;

import org.springframework.stereotype.Repository;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceNotFoundException;

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

    private static AtomicLong lastUsedId = new AtomicLong();
    private Map<Long, Invoice> invoices = new HashMap();

    @Override
    public final void saveInvoice(final Invoice invoice) {
        invoice.setId(lastUsedId.incrementAndGet());
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
    public final Optional<Invoice> getInvoiceById(final long id) {
        return Optional.ofNullable(invoices.get(id));
    }

    @Override
    public final List<Invoice> getInvoices() {
        return new ArrayList<>(invoices.values());
    }

    @Override
    public List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.MAX;
        }

        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate = endDate;

        return getInvoices()
                .stream()
                .filter(invoice -> invoice.getData()
                        .isAfter(finalStartDate))
                .filter(invoice -> invoice.getData()
                        .isBefore(finalEndDate))
                .collect(Collectors.toList());
    }
}
