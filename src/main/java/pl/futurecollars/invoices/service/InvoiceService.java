package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.futurecollars.invoices.database.invoice.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Service
@Validated
public class InvoiceService {

    @Autowired
    private Database database;

    public long saveInvoice(@Valid Invoice invoice) {
        return database.saveInvoice(invoice);
    }

    public List<Invoice> getInvoices() {
        return database.getInvoices();
    }

    public List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate) {
        return database.getInvoices(startDate, endDate);
    }

    public Optional<Invoice> getInvoice(long id) {
        return database.getInvoiceById(id);
    }

    public void updateInvoice(long id, @Valid Invoice updatedInvoice) {
        database.updateInvoice(id, updatedInvoice);
    }

    public void deleteInvoice(long id) {
        database.deleteInvoice(id);
    }
}
