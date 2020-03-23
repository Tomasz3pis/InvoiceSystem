package pl.futurecollars.invoices.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InMemoryDatabaseTest {

    @Mock
    private Invoice invoice;

    @Mock
    private Invoice secondInvoice;

    @Mock
    private Invoice thirdInvoice;

    @InjectMocks
    private InMemoryDatabase database;

    @Test
    void shouldSaveInvoice() {
        // Given
        Long id = 1L;

        // When
        database.saveInvoice(invoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));
        assertTrue(database.getInvoices().containsKey(id));
    }

    @Test
    void shouldSaveManyInvoices() {
        // Given
        Long id = 1L;
        Long secondId = 2L;
        Long thirdId = 3L;

        // When
        database.saveInvoice(invoice);
        database.saveInvoice(secondInvoice);
        database.saveInvoice(thirdInvoice);

        // Then
        assertThat(database.getInvoices().get(id), is(invoice));
        assertThat(database.getInvoices().get(secondId), is(secondInvoice));
        assertThat(database.getInvoices().get(thirdId), is(thirdInvoice));
    }

    @Test
    void shouldUpdateInvoiceGivenSameIdInvoice() {
        // Given
        Long id = 1L;
        when(secondInvoice.getId()).thenReturn(id);
        database.saveInvoice(invoice);

        // When
        database.updateInvoice(secondInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(secondInvoice)));
    }

    @Test
    void updateInvoiceShouldThrowExceptionGivenNotPresentInvoice() {
        // Given
        Long id = 1L;
        when(invoice.getId()).thenReturn(id);

        // When

        // Then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.updateInvoice(invoice));
        assertThat(exception.getMessage(), is(
                "Provided updatedInvoice does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }

    @Test
    void shouldDeleteInvoiceGivenId() {
        // Given
        Long id = 1L;
        database.saveInvoice(invoice);
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));

        // When
        database.deleteInvoice(id);

        // Then
        assertFalse(database.getInvoices().containsKey(id));
    }

    @Test
    void deleteInvoiceShouldThrowExceptionGivenNotExistingId() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> database.deleteInvoice(1L));
        assertThat(exception.getMessage(), is(
                "Provided id: "
                        + "1"
                        + " not found in Database."));
    }
}
