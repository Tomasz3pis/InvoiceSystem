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

import java.util.ArrayList;
import java.util.List;

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
        when(invoice.getId()).thenReturn(id);

        // When
        database.saveInvoice(invoice);

        // Then
        assertThat(database.getInvoiceById(id), is(invoice));
        assertTrue(database.getIdNumbers().contains(id));
        assertThat(database.getLastId(), is(id));
    }

    @Test
    void shouldSaveManyInvoices() {
        // Given
        String id = "20200101_0001";
        String secondId = "20200101_0002";
        String thirdId = "20200101_0003";
        when(invoice.getId()).thenReturn(id);
        when(secondInvoice.getId()).thenReturn(secondId);
        when(thirdInvoice.getId()).thenReturn(thirdId);

        List<Invoice> expectedInvoices = new ArrayList<>();
        expectedInvoices.add(invoice);
        expectedInvoices.add(secondInvoice);
        expectedInvoices.add(thirdInvoice);

        List<String> expectedIds = new ArrayList<>();
        expectedIds.add(id);
        expectedIds.add(secondId);
        expectedIds.add(thirdId);

        // When
        database.saveInvoice(invoice);
        database.saveInvoice(secondInvoice);
        database.saveInvoice(thirdInvoice);

        // Then
        assertThat(database.getInvoices(), is(expectedInvoices));
        assertThat(database.getIdNumbers(), is(expectedIds));
        assertThat(database.getLastId(), is(thirdId));
    }

    @Test
    void shouldThrowExceptionSavingExistingInvoice() {
        // Given
        String id = "20200101_0001";
        when(invoice.getId()).thenReturn(id);

        // When
        database.saveInvoice(invoice);

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                database.saveInvoice(invoice));
        assertThat(exception.getMessage(), is("Invoice with id: "
                + id
                + " already exists in database. It can only be updated."));
    }

    @Test
    void getInvoiceByIdShouldThrowExceptionGivenNotPresentId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                database.getInvoiceById("20200101_0001"));
        assertThat(exception.getMessage(), is("Provided id: "
                + "20200101_0001"
                + " not found in Database."));
    }

    @Test
    void getInvoiceByIdShouldThrowExceptionGivenNotPresentInvoice() {
        String id = "20200101_0001";
        database.addId(id);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                database.getInvoiceById(id));
        assertThat(exception.getMessage(), is("Provided id: "
                + id
                + " was found in Database without connected Invoice."
                + "Invoice with this id was never properly saved "
                + "or was not properly deleted from database"));
    }

    @Test
    void shouldUpdateInvoiceGivenSameIdInvoice() {
        // Given
        String id = "20200101_0001";
        when(invoice.getId()).thenReturn(id);
        when(secondInvoice.getId()).thenReturn(id);
        database.saveInvoice(invoice);

        // When
        database.updateInvoice(secondInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(secondInvoice));
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
        when(invoice.getId()).thenReturn(id);
        database.saveInvoice(invoice);
        assertThat(database.getInvoiceById(id), is(invoice));
        assertTrue(database.containsId(id));

        // When
        database.deleteInvoice(id);

        // Then
        assertFalse(database.containsId(id));
    }
}
