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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        Map<Long, Invoice> expectedInvoices = new HashMap<>();
        expectedInvoices.put(1L, invoice);
        when(database.getInvoices()).thenReturn(expectedInvoices);

        // When
        InvoiceService invoiceService = new InvoiceService(database);

        // Then
        assertThat(invoiceService.getInvoices(), is(expectedInvoices));
    }

    @Test
    void shouldSaveInvoiceGivenData() {
        // Given
        Long id = 1L;
        when(database.getInvoiceById(id)).thenReturn(Optional.of(invoice));

        // When
        injectedInvoiceService.saveInvoice(invoice);

        // Then
        assertThat(injectedInvoiceService.getInvoice(id),
                is(Optional.of(invoice)));
    }

    @Test
    void shouldUpdateInvoice() {
        // Given

        // When
        injectedInvoiceService.updateInvoice(invoice);

        // Then
        verify(database).updateInvoice(invoice);
    }

    @Test
    void shouldDeleteInvoice() {
        // Given
        Long id = 1L;

        // When
        injectedInvoiceService.deleteInvoice(id);

        // Then
        verify(database).deleteInvoice(id);
    }
}
