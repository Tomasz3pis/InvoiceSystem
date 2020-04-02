package pl.futurecollars.invoice.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import pl.futurecollars.invoice.TestInvoiceProvider;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceNotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class InMemoryDatabaseTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice invoice = testInvoiceProvider.getBaseInvoice();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

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

        //then
        assertThrows(InvoiceNotFoundException.class, () ->
                inMemoryDatabase.updateInvoice(5, updatedInvoice));
    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice baseInvoice = testInvoiceProvider.getBaseInvoice();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
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

        //then
        assertThrows(InvoiceNotFoundException.class, () ->
                inMemoryDatabase.deleteInvoice(5));
    }

    @Test
    void shouldReturnInvoice() {
        //given
        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice baseInvoice = testInvoiceProvider.getBaseInvoice();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when
        inMemoryDatabase.saveInvoice(baseInvoice);
        Invoice actual = inMemoryDatabase.getInvoiceById(baseInvoice.getId());

        //then
        assertEquals(actual, baseInvoice);
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
        Collection<Invoice> firstCase = inMemoryDatabase.getInvoices(null, null);
        Collection<Invoice> secondCase = inMemoryDatabase.getInvoices(LocalDate.of(2015, 1, 1), null);
        Collection<Invoice> thirdCase = inMemoryDatabase.getInvoices(null, LocalDate.of(2015, 1, 1));
        Collection<Invoice> fourthCase = inMemoryDatabase.getInvoices(LocalDate.of(2015, 1, 1), LocalDate.of(2016, 1, 1));

        //then
        assertEquals(List.of(firstInvoice, secondInvoice), firstCase);
        assertEquals(List.of(secondInvoice), secondCase);
        assertEquals(List.of(firstInvoice), thirdCase);
        assertEquals(List.of(), fourthCase);
    }

    private static Stream<Arguments> invoiceProvider() {

        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice firstInvoice = testInvoiceProvider.getInvoiceWith5Entries();
        Invoice thirdInvoice = testInvoiceProvider.getBaseInvoice();
        Invoice secondInvoice = testInvoiceProvider.getSameAsBaseInvoice();

        return Stream.of(
                Arguments.of(firstInvoice, secondInvoice),
                Arguments.of(secondInvoice, thirdInvoice),
                Arguments.of(firstInvoice, thirdInvoice)
        );
    }
}
