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
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
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
        String id = "20200101_0001";
        when(invoice.getSaleDate()).thenReturn(LocalDate.of(2020, 1, 1));

        // When
        database.saveInvoice(invoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));
        assertTrue(database.getInvoices().containsKey(id));
    }

    @Test
    void shouldSaveManyInvoices() {
        // Given
        String id = "20200101_0001";
        String secondId = "20200102_0002";
        String thirdId = "20200103_0003";

        when(invoice.getSaleDate()).thenReturn(LocalDate.of(2020, 1, 1));
        when(secondInvoice.getSaleDate()).thenReturn(LocalDate.of(2020, 1, 2));
        when(thirdInvoice.getSaleDate()).thenReturn(LocalDate.of(2020, 1, 3));

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
        String id = "20200101_0001";
        when(invoice.getSaleDate()).thenReturn(LocalDate.of(2020, 1, 1));
        when(secondInvoice.getId()).thenReturn(id);
        database.saveInvoice(invoice);

        // When
        database.updateInvoice(secondInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(secondInvoice)));
    }

    @Test
    void updateInvoiceShouldThrowExceptionGivenNotPresentInvoice() {
        String id = "20200101_0001";
        when(invoice.getId()).thenReturn(id);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                database.updateInvoice(invoice));
        assertThat(exception.getMessage(), is(
                "Provided updatedInvoice does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }

    @Test
    void shouldDeleteInvoiceGivenId() {
        // Given
        String id = "20200101_0001";
        when(invoice.getSaleDate()).thenReturn(LocalDate.of(2020, 1, 1));
        database.saveInvoice(invoice);
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));

        // When
        database.deleteInvoice(id);

        // Then
        assertFalse(database.getInvoices().containsKey(id));
    }

    @Test
    void deleteInvoiceShouldThrowExceptionGivenNotExistingId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                database.deleteInvoice("20200101_0001"));
        assertThat(exception.getMessage(), is(
                "Provided id: "
                        + "20200101_0001"
                        + " not found in Database."));
    }
}
