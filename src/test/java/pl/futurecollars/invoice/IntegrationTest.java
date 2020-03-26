package pl.futurecollars.invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.futurecollars.invoice.database.Database;
import pl.futurecollars.invoice.database.InMemoryDatabase;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.service.InvoiceService;

class IntegrationTest {

    private Database database = new InMemoryDatabase();
    private InvoiceService invoiceService = new InvoiceService(database);

    @BeforeEach
    public void cleanup() {
        database.getInvoices().forEach(invoice -> database.deleteInvoice(invoice.getId()));
    }

    @ParameterizedTest
    @ArgumentsSource(InvoiceProvider.class)
    void shouldSaveInvoiceInDatabase(Invoice firstInvoice, Invoice secondInvoice) {
        //given

        //when
        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);

        //then
        assertEquals(invoiceService.findInvoiceById(firstInvoice.getId()), firstInvoice);
        assertEquals(invoiceService.findInvoiceById(secondInvoice.getId()), secondInvoice);
        assertEquals(database.getInvoices().size(), 2);
    }

    @ParameterizedTest
    @ArgumentsSource(InvoiceProvider.class)
    void shouldUpdateInvoice(Invoice invoice, Invoice updatedInvoice) {
        //given

        //when
        invoiceService.saveInvoice(invoice);
        database.updateInvoice(invoice.getId(), updatedInvoice);

        //then
        assertEquals(database.getInvoices().size(), 1);
        assertEquals(database.getInvoiceById(invoice.getId()), updatedInvoice);
    }

    @ParameterizedTest
    @ArgumentsSource(InvoiceProvider.class)
    void shouldRemoveInvoiceFromDatabase(Invoice firstInvoice, Invoice secondInvoice) {
        //given

        //when
        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);
        database.deleteInvoice(firstInvoice.getId());

        //then
        assertEquals(database.getInvoices().size(), 1);
        assertThrows(NotExistingInvoiceException.class, () ->
                database.getInvoiceById(firstInvoice.getId()));
    }
}
