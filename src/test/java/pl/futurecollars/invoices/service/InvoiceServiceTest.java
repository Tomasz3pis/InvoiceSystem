package pl.futurecollars.invoices.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private Database database;

    @Mock
    private Invoice invoice;

    @InjectMocks
    private InvoiceService injectedInvoiceService;

    @Test
    void shouldConstructInvoiceServiceGivenDatabase() {
        // Given
        List<Invoice> expectedInvoices = new ArrayList<>();
        expectedInvoices.add(invoice);
        when(database.getInvoices()).thenReturn(expectedInvoices);

        // When
        InvoiceService invoiceService = new InvoiceService(database);

        // Then
        assertThat(invoiceService.getInvoices(), is(expectedInvoices));
    }

    @Test
    void shouldSaveInvoiceGivenData() {
        // Given
        String id = "20200101_0001";
        when(database.getInvoiceById(id)).thenReturn(invoice);

        // When
        injectedInvoiceService.saveInvoice(invoice);

        // Then
        assertThat(injectedInvoiceService.getInvoice(id), is(invoice));
    }

    @Test
    void shouldUpdateInvoice() {
        injectedInvoiceService.updateInvoice(invoice);
        verify(database).updateInvoice(invoice);
    }

    @Test
    void shouldDeleteInvoice() {
        String id = "20200101_0001";
        injectedInvoiceService.deleteInvoice(id);
        verify(database).deleteInvoice(id);
    }
}
