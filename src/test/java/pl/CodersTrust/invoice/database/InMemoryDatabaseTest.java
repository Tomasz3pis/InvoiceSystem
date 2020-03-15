package pl.CodersTrust.invoice.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.CodersTrust.invoice.model.Invoice;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
class InMemoryDatabaseTest {


    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);

        //then
        Assert.assertTrue(imd.getInvoices().contains(invoice));

    }

    @Test
    void shouldPutNewInvoiceInPlaceOfOldInvoice() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice newInvoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.updateInvoice(invoice, newInvoice);

        //then
        Assert.assertThat(imd.getInvoiceById(invoice.getId()), is(newInvoice));
        Assert.assertThat(imd.getInvoices().size(), is(1));

    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.deleteInvoice(invoice);

        //then
        Assert.assertThat(imd.getInvoices(), is(empty()));

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
        Assert.assertEquals(actual, invoice);

    }

    @Test
    void shouldReturnEmptyListOfInvoices() {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        Collection<Invoice> actual = imd.getInvoices();

        //then
        Assert.assertThat(actual, is(empty()));

    }

    @Test
    void shouldReturnListOfInvoices() {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice invoice2 = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        imd.saveInvoice(invoice);
        imd.saveInvoice(invoice2);
        Collection<Invoice> actual = imd.getInvoices();

        //then
        Assert.assertThat(actual, hasItems(invoice, invoice2));

    }
}
