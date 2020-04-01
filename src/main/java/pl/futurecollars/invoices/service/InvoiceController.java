package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/invoices")
@Validated
public class InvoiceController {

    @Autowired
    private Database database;
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getInvoices(@RequestParam(name = "start", required = false) @DateTimeFormat(iso = ISO.DATE)
                                             LocalDate startDate,
                                     @RequestParam(name = "end", required = false) @DateTimeFormat(iso = ISO.DATE)
                                             LocalDate endDate) {
        return invoiceService.getInvoices(startDate, endDate);
    }

    @GetMapping("/{id}")
    public Optional<Invoice> getInvoiceById(@PathVariable("id") @Min(1) long id) {
        return invoiceService.getInvoice(id);
    }

    @PostMapping
    public long saveInvoice(@Valid @RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @PutMapping("/{id}")
    public void updateInvoice(@PathVariable("id") @Min(1) long id, @Valid @RequestBody Invoice updatedInvoice) {
        invoiceService.updateInvoice(id, updatedInvoice);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable("id") @Min(1) long id) {
        invoiceService.deleteInvoice(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(
                "Validation failed with message: " + exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
