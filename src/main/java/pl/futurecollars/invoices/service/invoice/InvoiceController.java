package pl.futurecollars.invoices.service.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.database.invoice.Database;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;

@RestController
public class InvoiceController implements InvoiceApi {

    @Autowired
    private Database database;

    @Autowired
    private InvoiceService invoiceService;

    public List<Invoice> getInvoices(@RequestParam(name = "startDate", required = false) String startDate,
                                     @RequestParam(name = "endDate", required = false) String endDate) {
        LocalDate startDateLocal = null;
        LocalDate endDateLocal = null;
        if (startDate != null) {
            startDateLocal = LocalDate.parse(startDate);
        }
        if (endDate != null) {
            endDateLocal = LocalDate.parse(endDate);
        }
        return invoiceService.getInvoices(startDateLocal, endDateLocal);
    }

    public Invoice getInvoiceById(@PathVariable("id") long id) {
        if (invoiceService.getInvoice(id).isPresent()) {
            return invoiceService.getInvoice(id).get();
        } else {
            throw new InvoiceNotFoundException(id);
        }
    }

    public long saveInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    public void updateInvoice(@PathVariable("id") long id, @RequestBody Invoice updatedInvoice) {
        invoiceService.updateInvoice(id, updatedInvoice);
    }

    public void deleteInvoice(@PathVariable("id") long id) {
        if (invoiceService.getInvoice(id).isEmpty()) {
            throw new InvoiceNotFoundException(id);
        }
        invoiceService.deleteInvoice(id);
    }
}
