package pl.futurecollars.invoice.service;

import org.springframework.stereotype.Service;
import pl.futurecollars.invoice.dto.InvoiceDTO;
import pl.futurecollars.invoice.model.Invoice;

@Service
public class InvoiceTransformer {

    private final CompanyService companyService;

    public InvoiceTransformer(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Invoice fromDto(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDTO.getId());
        invoice.setEntries(invoiceDTO.getEntries());
        invoice.setDate(invoiceDTO.getDate());
        invoice.setBuyer(companyService.getCompany(invoiceDTO.getBuyerID()));
        invoice.setSeller(companyService.getCompany(invoiceDTO.getSeller()));
        return invoice;
    }

}
