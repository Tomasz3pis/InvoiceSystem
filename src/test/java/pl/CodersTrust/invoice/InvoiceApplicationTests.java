package pl.CodersTrust.invoice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.CodersTrust.invoice.database.InMemoryDatabase;
import pl.CodersTrust.invoice.model.Company;
import pl.CodersTrust.invoice.model.Invoice;
import pl.CodersTrust.invoice.model.InvoiceEntry;
import pl.CodersTrust.invoice.model.Vat;
import pl.CodersTrust.invoice.service.InvoiceBook;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class InvoiceApplicationTests {

    @Test
    void contextLoads() {
        Company company1 = new Company(7896541234L, "St steerra", "GBC");
        Company company2 = new Company(9080081223L, "Bonsa st", "Unomi");
        Invoice invoice1 = new Invoice(company1, company2, LocalDate.now(), List.of(new InvoiceEntry("do this", new BigDecimal(1500), Vat.VAT_23)));
        Invoice invoice2 = new Invoice(company2, company1, LocalDate.now(), List.of(new InvoiceEntry("do that", new BigDecimal(1600), Vat.VAT_8)));
        InMemoryDatabase imb = new InMemoryDatabase();
        InvoiceBook ib = new InvoiceBook();

        imb.saveInvoice(invoice1);
        imb.saveInvoice(invoice2);
        imb.deleteInvoice(invoice2);
        imb.updateInvoice(invoice1, invoice2);
        imb.getInvoices();
        imb.getInvoiceById(1L);

        ib.saveInvoiceInDatabase(invoice1);
        ib.searchInvoiceById(1L);
    }

    @Test
    void applicationStarts() {
        InvoiceApplication.main(new String[] {});
    }
}
