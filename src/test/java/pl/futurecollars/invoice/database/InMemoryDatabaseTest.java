package pl.futurecollars.invoice.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.futurecollars.invoice.TestInvoiceProvider;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceNotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

class InMemoryDatabaseTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice invoice = testInvoiceProvider.getBaseInvoice();

        //when
        inMemoryDatabase.saveInvoice(invoice);

        //then
        assertTrue(inMemoryDatabase.getInvoices().contains(invoice));
    }

    @Test
    void shouldPutNewInvoiceInPlaceOfOldInvoice() {
        //given
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice invoice = testInvoiceProvider.getBaseInvoice();
        Invoice updatedInvoice = testInvoiceProvider.getInvoiceWith5Entries();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        inMemoryDatabase.saveInvoice(invoice);

        //when
        inMemoryDatabase.updateInvoice(invoice.getId(), updatedInvoice);

        //then
        assertEquals(inMemoryDatabase.getInvoices().size(), 1);
        assertEquals(inMemoryDatabase.getInvoiceById(invoice.getId()), updatedInvoice);
    }

    @Test
    void shouldThrowNotExistingInvoiceExceptionWhenUpdatingInvoice() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice updatedInvoice = testInvoiceProvider.getBaseInvoice();
        //when
        long invalidId = 8;

        //then
        assertThrows(InvoiceNotFoundException.class, () ->
                inMemoryDatabase.updateInvoice(invalidId, updatedInvoice));
    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice baseInvoice = testInvoiceProvider.getBaseInvoice();
        inMemoryDatabase.saveInvoice(baseInvoice);

        //when
        inMemoryDatabase.deleteInvoice(baseInvoice.getId());

        //then
        assertTrue(inMemoryDatabase.getInvoices().isEmpty());
    }

    @Test
    void shouldThrowNotExistingInvoiceExceptionWhenDeletingInvoice() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when
        long invalidId = 8;

        //then
        assertThrows(InvoiceNotFoundException.class, () ->
                inMemoryDatabase.deleteInvoice(invalidId));
    }

    @Test
    void shouldReturnInvoice() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice invoice = testInvoiceProvider.getBaseInvoice();

        //when
        inMemoryDatabase.saveInvoice(invoice);
        Optional<Invoice> actual = inMemoryDatabase.getInvoiceById(invoice.getId());

        //then
        assertTrue(actual.isPresent());
        assertEquals(actual.get(), invoice);
    }

    @Test
    void shouldReturnEmptyListOfInvoices() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when
        Collection<Invoice> actual = inMemoryDatabase.getInvoices();

        //then
        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldReturnListOfInvoices() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice baseInvoice = testInvoiceProvider.getBaseInvoice();
        Invoice invoiceWith5Entries = testInvoiceProvider.getInvoiceWith5Entries();

        //when
        inMemoryDatabase.saveInvoice(baseInvoice);
        inMemoryDatabase.saveInvoice(invoiceWith5Entries);
        Collection<Invoice> actual = inMemoryDatabase.getInvoices();

        //then
        assertEquals(actual.size(), 2);
        assertIterableEquals(actual, List.of(baseInvoice, invoiceWith5Entries));
    }

    @Test
    void shouldReturnInvoicesInTimePeriod() {
        //before
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice firstInvoice = testInvoiceProvider.getInvoiceWith5Entries();
        Invoice secondInvoice = testInvoiceProvider.getBaseInvoice();
        inMemoryDatabase.saveInvoice(firstInvoice);
        inMemoryDatabase.saveInvoice(secondInvoice);

        //when
        Collection<Invoice> nullDates = inMemoryDatabase.getInvoices(null, null);
        Collection<Invoice> nullStartDate = inMemoryDatabase.getInvoices(null, LocalDate.of(2015, 1, 1));
        Collection<Invoice> nullEndDate = inMemoryDatabase.getInvoices(LocalDate.of(2015, 1, 1), null);
        Collection<Invoice> validDates = inMemoryDatabase.getInvoices(LocalDate.of(2015, 1, 1), LocalDate.of(2016, 1, 1));

        //then
        assertEquals(List.of(firstInvoice, secondInvoice), nullDates);
        assertEquals(List.of(firstInvoice), nullStartDate);
        assertEquals(List.of(secondInvoice), nullEndDate);
        assertEquals(List.of(), validDates);
    }
}
