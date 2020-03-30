package pl.futurecollars.invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.futurecollars.invoice.database.Database;
import pl.futurecollars.invoice.database.InMemoryDatabase;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.service.InvoiceService;

class IntegrationTest {

    private Database database = new InMemoryDatabase();
    private InvoiceService invoiceService = new InvoiceService(database);
    private TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();

    @BeforeEach
    public void cleanup() {
        database.getInvoices().forEach(invoice -> database.deleteInvoice(invoice.getId()));
    }

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice firstInvoice = testInvoiceProvider.getBaseInvoice();
        Invoice secondInvoice = testInvoiceProvider.getInvoiceWith5Entries();

        //when
        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);

        //then
        assertEquals(database.getInvoices().size(), 2);
        assertTrue(database.getInvoices().contains(firstInvoice));
        assertTrue(database.getInvoices().contains(secondInvoice));
        assertEquals(invoiceService.findInvoiceById(firstInvoice.getId()), firstInvoice);
        assertEquals(invoiceService.findInvoiceById(secondInvoice.getId()), secondInvoice);
    }

    @Test
    void shouldUpdateInvoice() {
        //given
        Invoice invoice = testInvoiceProvider.getBaseInvoice();
        Invoice updatedInvoice = testInvoiceProvider.getInvoiceWith5Entries();

        //when
        invoiceService.saveInvoice(invoice);
        database.updateInvoice(invoice.getId(), updatedInvoice);

        //then
        assertEquals(database.getInvoices().size(), 1);
        assertTrue(database.getInvoices().contains(updatedInvoice));
        assertFalse(database.getInvoices().contains(invoice));
    }

    @Test
    void shouldRemoveInvoiceFromDatabase() {
        //given
        Invoice firstInvoice = testInvoiceProvider.getBaseInvoice();
        Invoice secondInvoice = testInvoiceProvider.getInvoiceWith5Entries();

        //when
        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);
        database.deleteInvoice(firstInvoice.getId());

        //then
        assertEquals(database.getInvoices().size(), 1);
        assertTrue(database.getInvoices().contains(secondInvoice));
        assertFalse(database.getInvoices().contains(firstInvoice));
    }
}
