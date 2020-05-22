package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Service
public class CompanyService {

    @Autowired
    private Database database;

    public List<Invoice> getInvoices(LocalDate startDateLocal, LocalDate endDateLocal) {
        return Collections.emptyList();
    }

    public Optional<Invoice> getInvoice(long id) {
        return Optional.empty();
    }

    public long saveInvoice(@Valid Invoice invoice) {
        return invoice.getId();
    }

    public void updateInvoice(long id, Invoice updatedInvoice) {

    }

    public void deleteInvoice(long id) {

    }
}
