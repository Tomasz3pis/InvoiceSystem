package pl.CodersTrust.invoice.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.model.Invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        assertEquals(invoice, actual);
        verify(database, times(1)).getInvoiceById(invoice.getId());
    }

    @Test
    void updateInvoice() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Invoice newInvoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        InvoiceBook ib = new InvoiceBook(database);
        doNothing().when(database).updateInvoice(invoice.getId(), newInvoice);

        //when
        ib.updateInvoice(invoice.getId(), newInvoice);

        //then
        assertEquals(invoice.getId(), newInvoice.getId());
        verify(database, times(1)).updateInvoice(invoice.getId(), newInvoice);
    }

    @Test
    void deleteInvoice() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        InvoiceBook ib = new InvoiceBook(database);
        doNothing().when(database).deleteInvoice(invoice.getId());

        //when
        ib.deleteInvoice(invoice.getId());

        //then
        verify(database, times(1)).deleteInvoice(invoice.getId());
    }
}
