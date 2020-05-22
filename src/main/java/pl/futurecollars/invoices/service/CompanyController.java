package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/{companyId}")
@Validated
public class CompanyController {

    @Autowired
    private Database database;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ValidatingService validatingService;

    @GetMapping("/invoices")
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
        return companyService.getInvoices(startDateLocal, endDateLocal);
    }

    @GetMapping("/invoices/{invoiceId}")
    public Invoice getInvoiceById(@PathVariable("id") long id) {
        if (companyService.getInvoice(id).isEmpty()) {
            throw new InvoiceNotFoundException(id);
        }
        return companyService.getInvoice(id).get();
    }

    @PostMapping("/invoices")
    public long saveInvoice(@RequestBody Invoice invoice) {
        validatingService.validateInput(invoice);
        return companyService.saveInvoice(invoice);
    }

    @PutMapping("/invoices/{invoiceId}")
    public void updateInvoice(@PathVariable("id") long id, @RequestBody Invoice updatedInvoice) {
        validatingService.validateInput(updatedInvoice);
        companyService.updateInvoice(id, updatedInvoice);
    }

    @DeleteMapping("/invoices/{invoiceId}")
    public void deleteInvoice(@PathVariable("id") long id) {
        if (companyService.getInvoice(id).isEmpty()) {
            throw new InvoiceNotFoundException(id);
        }
        companyService.deleteInvoice(id);
    }
}
