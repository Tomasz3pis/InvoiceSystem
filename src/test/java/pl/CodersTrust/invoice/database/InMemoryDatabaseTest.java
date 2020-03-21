package pl.CodersTrust.invoice.database;

import org.junit.jupiter.api.Test;
import pl.CodersTrust.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryDatabaseTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);

        //then
        assertTrue(imd.getInvoices().contains(invoice));
    }

    @Test
    void shouldPutNewInvoiceInPlaceOfOldInvoice() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice newInvoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.updateInvoice(invoice.getId(), newInvoice);

        //then
        assertEquals(imd.getInvoiceById(invoice.getId()), newInvoice);
        assertEquals(imd.getInvoices().size(), 1);
    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.deleteInvoice(invoice.getId());

        //then
        assertTrue(imd.getInvoices().isEmpty());
    }

    @Test
    void shouldReturnInvoice() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);
        Invoice actual = imd.getInvoiceById(invoice.getId());

        //then
        assertEquals(actual, invoice);
    }

    @Test
    void shouldReturnEmptyListOfInvoices() {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        Collection<Invoice> actual = imd.getInvoices();

        //then
        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldReturnListOfInvoices() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice invoice2 = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);
        imd.saveInvoice(invoice2);
        Collection<Invoice> actual = imd.getInvoices();

        //then
        assertIterableEquals(actual, List.of(invoice, invoice2));
        assertEquals(actual.size(), 2);
    }
}
