package pl.CodersTrust.invoice.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.model.Invoice;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
class InvoiceBookTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        doNothing().when(database).saveInvoice(invoice);

        //when
        database.saveInvoice(invoice);

        //then
        verify(database, times(1)).saveInvoice(invoice);


    }

    @Test
    void shouldReturnInvoiceById() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        when(database.getInvoiceById(invoice.getId())).thenReturn(invoice);

        //when
        Invoice actual = database.getInvoiceById(invoice.getId());

        //then
        Assert.assertEquals(invoice, actual);

    }

}
