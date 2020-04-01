package pl.futurecollars.invoices.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        long id = 1L;

        // When
        database.saveInvoice(invoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));
    }

    @Test
    void shouldSaveManyInvoices() {
        // Given
        long id = 1L;
        long secondId = 2L;
        long thirdId = 3L;

        // When
        database.saveInvoice(invoice);
        database.saveInvoice(secondInvoice);
        database.saveInvoice(thirdInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));
        assertThat(database.getInvoiceById(secondId), is(Optional.of(secondInvoice)));
        assertThat(database.getInvoiceById(thirdId), is(Optional.of(thirdInvoice)));
    }

    @Test
    void shouldUpdateInvoiceGivenSameIdInvoice() {
        // Given
        long id = 1L;
        database.saveInvoice(invoice);

        // When
        database.updateInvoice(id, secondInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(secondInvoice)));
    }

    @Test
    void updateInvoiceShouldThrowExceptionGivenNotPresentInvoice() {
        // Given
        long id = 1L;

        // When

        // Then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.updateInvoice(id, invoice));
        assertThat(exception.getMessage(), is(
                "Provided updatedInvoice does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }

    @Test
    void shouldDeleteInvoiceGivenId() {
        // Given
        long id = 1L;
        database.saveInvoice(invoice);
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));

        // When
        database.deleteInvoice(id);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.empty()));
    }

    @Test
    void deleteInvoiceShouldThrowExceptionGivenNotExistingId() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.deleteInvoice(1L));
        assertThat(exception.getMessage(), is(
                "Provided id: "
                        + "1"
                        + " not found in Database."));
    }
}
