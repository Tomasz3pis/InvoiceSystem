package pl.CodersTrust.invoice.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import pl.CodersTrust.invoice.model.Invoice;


import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
class InMemoryDatabaseTest {


    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice invoice = mock(Invoice.class);
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);

        //then
        Assert.assertTrue(imd.getInvoices().contains(invoice));

    }

    @Test
    void shouldPutNewInvoiceInPlaceOfOldInvoice() {
        //given
        Invoice invoice = mock(Invoice.class);
        Invoice newInvoice = mock(Invoice.class);
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.updateInvoice(invoice, newInvoice);

        //then
        Assert.assertThat(imd.getInvoices(), contains(invoice));
    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        Invoice invoice = mock(Invoice.class);
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
        Invoice invoice = mock(Invoice.class);
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
        Invoice invoice = mock(Invoice.class);
        Invoice invoice2 = mock(Invoice.class);

        //when
        imd.saveInvoice(invoice);
        imd.saveInvoice(invoice2);
        Collection<Invoice> actual = imd.getInvoices();

        //then
        Assert.assertThat(actual, hasItems(invoice, invoice2));

    }
}
