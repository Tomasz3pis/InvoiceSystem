package pl.CodersTrust.invoice.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.model.Invoice;


import static org.mockito.Mockito.doNothing;
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
        InvoiceBook ib = new InvoiceBook(database);

        //when
        ib.saveInvoiceInDatabase(invoice);

        //then
        verify(database, times(1)).saveInvoice(invoice);


    }

    @Test
    void shouldReturnInvoiceById() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        InvoiceBook ib = new InvoiceBook(database);
        when(database.getInvoiceById(invoice.getId())).thenReturn(invoice);

        //when
        Invoice actual = ib.searchInvoiceById(invoice.getId());

        //then
        Assert.assertEquals(invoice, actual);
        verify(database, times(1)).getInvoiceById(invoice.getId());

    }

}
