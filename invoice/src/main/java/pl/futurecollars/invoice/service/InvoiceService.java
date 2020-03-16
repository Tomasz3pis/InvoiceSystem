package pl.futurecollars.invoice.service;

import org.springframework.stereotype.Service;
import pl.futurecollars.invoice.database.InMemoryInvoiceDatabase;
import pl.futurecollars.invoice.dto.InvoiceDTO;
import pl.futurecollars.invoice.model.Invoice;

import java.util.List;

@Service
public class InvoiceService {

    private final InMemoryInvoiceDatabase database;
    private final InvoiceTransformer invoiceTransformer;

    public InvoiceService(InMemoryInvoiceDatabase database, InvoiceTransformer invoiceTransformer) {
        this.database = database;
        this.invoiceTransformer = invoiceTransformer;
    }

    public Invoice saveInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceTransformer.fromDto(invoiceDTO);
        return database.save(invoice);
    }

    public List<Invoice> getInvoices() {
        return database.getAll();
    }

    public Invoice getInvoice(Long id) {

        return database.getById(id);
    }

    public Invoice updateInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceTransformer.fromDto(invoiceDTO);
        return database.update(invoice);
    }

    public void deleteInvoice(Long id) {
        database.delete(id);
    }
}
