package pl.futurecollars.invoices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/invoices")
@Validated
public class InvoiceController {

    @Autowired
    private Database database;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ValidatingService validatingService;

    @GetMapping
    public List<Invoice> getInvoices(@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = ISO.DATE)
                                             LocalDate startDate,
                                     @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = ISO.DATE)
                                             LocalDate endDate) {
        return invoiceService.getInvoices(startDate, endDate);
    }

    @GetMapping("/{id}")
    public Optional<Invoice> getInvoiceById(@PathVariable("id") @Min(1) long id) {
        return invoiceService.getInvoice(id);
    }

    @PostMapping
    public long saveInvoice(@RequestBody Invoice invoice) throws JsonProcessingException {
        validatingService.validateInput(invoice);
        return invoiceService.saveInvoice(invoice);
    }

    @PutMapping("/{id}")
    public void updateInvoice(@PathVariable("id") @Min(1) long id, @RequestBody Invoice updatedInvoice) {
        validatingService.validateInput(updatedInvoice);
        invoiceService.updateInvoice(id, updatedInvoice);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable("id") @Min(1) long id) {
        invoiceService.deleteInvoice(id);
    }
}
