package pl.futurecollars.invoice.database;

import org.springframework.stereotype.Repository;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.service.IdGenerator;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryInvoiceDatabase implements Database<Invoice, Long> {
    private List<Invoice> invoices = new ArrayList<>();

    public List<Invoice> getInvoices() {
        return invoices;
    }

    private final IdGenerator idGenerator;

    public InMemoryInvoiceDatabase(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Invoice save(Invoice invoice) {
        if (invoice.getId() != null) {
            throw new IllegalArgumentException("Id should be empty in post request");
        }
        invoice.setId(idGenerator.getId());
        if (invoices.add(invoice)) {
            return invoice;
        }
        return null;
    }

    @Override
    public List<Invoice> getAll() {
        return invoices;
    }

    @Override
    public Invoice getById(Long id) {
        return invoices.stream()
                .filter(invoice -> invoice.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provide id" + id + "is not found"));
    }

    @Override
    public Invoice update(Invoice updatedInvoice) {
        int updateIndex = invoices.indexOf(getById(updatedInvoice.getId()));
        invoices.set(updateIndex, updatedInvoice);
        return updatedInvoice;
    }

    @Override
    public void delete(Long id) {
        if(!invoices.removeIf(invoice -> invoice.getId().equals(id))) {
            throw new IllegalArgumentException("Provide id" + id + "is not found");
        }
    }
}
