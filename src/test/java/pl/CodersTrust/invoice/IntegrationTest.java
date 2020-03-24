package pl.coderstrust.invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.database.InMemoryDatabase;
import pl.CodersTrust.invoice.model.Company;
import pl.CodersTrust.invoice.model.Invoice;
import pl.CodersTrust.invoice.model.InvoiceEntry;
import pl.CodersTrust.invoice.model.Vat;
import pl.coderstrust.invoice.service.InvoiceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IntegrationTest {

    private static Database database = new InMemoryDatabase();
    private static InvoiceService invoiceService = new InvoiceService(database);

    @BeforeEach
    public void cleanup() {
        database.getInvoices().forEach(invoice -> database.deleteInvoice(invoice.getId()));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldSaveInvoiceInDatabase(Invoice invoice1, Invoice invoice2) {

        invoiceService.saveInvoiceInDatabase(invoice1);
        invoiceService.saveInvoiceInDatabase(invoice2);

        assertEquals(invoiceService.searchInvoiceById(invoice1.getId()), invoice1);
        assertEquals(invoiceService.searchInvoiceById(invoice2.getId()), invoice2);
        assertEquals(database.getInvoices().size(), 2);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldChangeInvoice(Invoice invoice3, Invoice invoice4) {

        invoiceService.saveInvoiceInDatabase(invoice3);
        invoice4.setData(LocalDate.of(2018, 6, 8));
        invoice4.setBuyer(new Company(8900988889L, "Monkey st", "Snek"));
        invoice4.setEntries(invoice3.getEntries());
        invoice4.setSeller(new Company(1233211223L, "Unknow", "Hash Corp"));
        database.updateInvoice(invoice3.getId(), invoice4);

        assertEquals(database.getInvoiceById(invoice3.getId()), invoice4);
        assertEquals(invoice4.getData(), LocalDate.of(2018, 6, 8));
        assertEquals(database.getInvoices().size(), 1);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldRemoveInvoiceFromDatabase(Invoice invoice5, Invoice invoice6) {

        invoiceService.saveInvoiceInDatabase(invoice5);
        invoiceService.saveInvoiceInDatabase(invoice6);
        database.deleteInvoice(invoice5.getId());

        assertNull(database.getInvoiceById(invoice5.getId()));
        assertEquals(database.getInvoices().size(), 1);
    }

    private static Stream<Arguments> dataProvider() {
        Company company1 = new Company(7896541234L, "St steerra", "GBC");
        Company company2 = new Company(9080081223L, "Bonsa st", "Unomi");
        InvoiceEntry entry1 = new InvoiceEntry("do this", new BigDecimal(1500), Vat.VAT_23);
        InvoiceEntry entry2 = new InvoiceEntry("do that", new BigDecimal(600), Vat.VAT_8);
        Invoice invoice1 = new Invoice(company1, company2, LocalDate.now(), List.of(entry1, entry2));
        Invoice invoice2 = new Invoice(company2, company1, LocalDate.now(), List.of(entry1));

        return Stream.of(Arguments.of(invoice1, invoice2));
    }
}
