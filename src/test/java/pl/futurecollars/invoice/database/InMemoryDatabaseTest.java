package pl.futurecollars.invoice.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.futurecollars.invoice.NotExistingInvoiceException;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.Invoice.InvoiceBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class InMemoryDatabaseTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when
        inMemoryDatabase.saveInvoice(invoice);

        //then
        assertTrue(inMemoryDatabase.getInvoices().contains(invoice));
    }

    @Test
    void shouldPutNewInvoiceInPlaceOfOldInvoice() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        Invoice updatedInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
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
        Invoice updatedInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        //when

        //then
        assertThrows(NotExistingInvoiceException.class, () -> {
            inMemoryDatabase.updateInvoice(5, updatedInvoice);

        });
    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        inMemoryDatabase.saveInvoice(invoice);

        //when
        inMemoryDatabase.deleteInvoice(invoice.getId());

        //then
        assertTrue(inMemoryDatabase.getInvoices().isEmpty());
    }

    @Test
    void shouldThrowNotExistingInvoiceExceptionWhenDeletingInvoice() {
        //given
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when

        //then
        assertThrows(NotExistingInvoiceException.class, () -> {
            inMemoryDatabase.deleteInvoice(5);
        });
    }

    @Test
    void shouldReturnInvoice() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when
        inMemoryDatabase.saveInvoice(invoice);
        Invoice actual = inMemoryDatabase.getInvoiceById(invoice.getId());

        //then
        assertEquals(actual, invoice);
        assertEquals(invoice.getId(), InMemoryDatabase.getLastUsedId().longValue());
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
        Invoice firstInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        Invoice secondInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();

        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

        //when
        inMemoryDatabase.saveInvoice(firstInvoice);
        inMemoryDatabase.saveInvoice(secondInvoice);
        Collection<Invoice> actual = inMemoryDatabase.getInvoices();

        //then
        assertEquals(actual.size(), 2);
        assertIterableEquals(actual, List.of(firstInvoice, secondInvoice));
    }
}
