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
    void shouldSaveInvoiceGivenData() {
        // Given
        long id = 1L;
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
        injectedInvoiceService.updateInvoice(1L, invoice);

        // Then
        verify(database).updateInvoice(1L, invoice);
    }

    @Test
    void shouldDeleteInvoice() {
        // Given
        long id = 1L;

        // When
        injectedInvoiceService.deleteInvoice(id);

        // Then
        verify(database).deleteInvoice(id);
    }
}
