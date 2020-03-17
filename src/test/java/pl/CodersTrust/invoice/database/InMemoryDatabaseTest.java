package pl.CodersTrust.invoice.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.CodersTrust.invoice.model.Invoice;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
class InMemoryDatabaseTest {


    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldSaveInvoiceInDatabase(Invoice invoice) {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);

        //then
        Assert.assertTrue(imd.getInvoices().contains(invoice));

    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldPutNewInvoiceInPlaceOfOldInvoice(Invoice invoice, Invoice newInvoice) {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.updateInvoice(invoice.getId(), newInvoice);

        //then
        Assert.assertThat(imd.getInvoiceById(invoice.getId()), is(newInvoice));
        Assert.assertThat(imd.getInvoices().size(), is(1));

    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldDeleteGivenInvoiceFromDatabase(Invoice invoice) {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        imd.deleteInvoice(invoice.getId());

        //then
        Assert.assertThat(imd.getInvoices(), is(empty()));

    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnInvoice(Invoice invoice) {
        //given
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

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnListOfInvoices(Invoice invoice, Invoice invoice2) {
        //given
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);
        imd.saveInvoice(invoice2);
        Collection<Invoice> actual = imd.getInvoices();

        //then
        Assert.assertThat(actual, hasItems(invoice, invoice2));
        Assert.assertThat(actual.size(), is(2));

    }

    private static Stream<Arguments> invoiceProvider() {
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice invoice1 = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        return Stream.of(Arguments.of(invoice, invoice1));
    }
}
