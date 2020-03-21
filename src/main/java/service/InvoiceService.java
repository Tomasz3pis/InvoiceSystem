package service;

import org.springframework.stereotype.Service;
import pl.futurecollars.invoice.model.Invoice;
import java.util.Collection;

@Service
public class InvoiceService {

    private InvoiceService invoiceService;

    public InvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public Collection<Invoice> getInvoices() {
        return invoiceService.getInvoices();//.values
    }
}
