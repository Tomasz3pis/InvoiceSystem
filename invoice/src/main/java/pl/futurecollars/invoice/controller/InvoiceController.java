package pl.futurecollars.invoice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoice.dto.InvoiceDTO;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.service.InvoiceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping()
    public List<Invoice> getAll() {
        return invoiceService.getInvoices();
    }

    @GetMapping("/{id}")
    public Invoice getById(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) {
        return invoiceService.saveInvoice(invoiceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable long id) {
        invoiceService.deleteInvoice(id);
    }

    @PutMapping()
    public Invoice updateInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(invoiceDTO);
    }
}
