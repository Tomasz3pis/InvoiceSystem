package pl.futurecollars.invoice.service;

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
import pl.futurecollars.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/invoices")
@Validated
public class InvoiceController {

    private InvoiceService invoiceService;

    InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getInvoicesInDataRange(@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
                                                @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return new ArrayList<>(invoiceService.getInvoices(startDate, endDate));
    }

    @GetMapping("/{id}")
    public Optional<Invoice> findInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.findInvoiceById(id);
    }

    @PostMapping
    public void saveInvoice(@Valid @RequestBody Invoice invoice) {
        invoiceService.saveInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
    }

    @PutMapping("/{id}")
    public void updateInvoice(@PathVariable("id") Long id, @Valid @RequestBody Invoice updatedInvoice) {
        invoiceService.updateInvoice(id, updatedInvoice);
    }
}
