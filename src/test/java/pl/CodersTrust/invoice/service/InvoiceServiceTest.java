package pl.coderstrust.invoice.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.model.Invoice;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InvoiceServiceTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        doNothing().when(database).saveInvoice(invoice);
        InvoiceService ib = new InvoiceService(database);

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
        InvoiceService ib = new InvoiceService(database);
        when(database.getInvoiceById(invoice.getId())).thenReturn(invoice);

        //when
        Invoice actual = ib.searchInvoiceById(invoice.getId());

        //then
        assertEquals(invoice, actual);
        verify(database, times(1)).getInvoiceById(invoice.getId());
    }

    @Test
    void shouldReturnAllInvoices() {
        //given
        Database database = Mockito.mock(Database.class);
        InvoiceService ib = new InvoiceService(database);

        //when
        when(ib.getAllInvoices()).thenReturn(Collections.emptyList());

        //then
        assertEquals(ib.getAllInvoices(), Collections.emptyList());
        verify(database, times(1)).getInvoices();
    }

    @Test
    void updateInvoice() {
        //given
        Invoice invoice = Mockito.mock(Invoice.class);
        Invoice newInvoice = Mockito.mock(Invoice.class);
        Database database = Mockito.mock(Database.class);
        InvoiceService ib = new InvoiceService(database);
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
        InvoiceService ib = new InvoiceService(database);
        doNothing().when(database).deleteInvoice(invoice.getId());

        //when
        ib.deleteInvoice(invoice.getId());

        //then
        verify(database, times(1)).deleteInvoice(invoice.getId());
    }
}
