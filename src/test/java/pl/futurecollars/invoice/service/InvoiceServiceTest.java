package pl.futurecollars.invoice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoice.database.Database;
import pl.futurecollars.invoice.model.Invoice;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private Invoice invoice;

    @Mock
    private Invoice updatedInvoice;

    @Mock
    private Database database;

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        doNothing().when(database).saveInvoice(invoice);
        InvoiceService invoiceService = new InvoiceService(database);

        //when
        invoiceService.saveInvoice(invoice);

        //then
        verify(database, times(1)).saveInvoice(invoice);
    }

    @Test
    void shouldReturnInvoiceById() {
        //given
        InvoiceService invoiceService = new InvoiceService(database);
        when(database.getInvoiceById(invoice.getId())).thenReturn(Optional.of(invoice));

        //when
        Optional<Invoice> actual = invoiceService.findInvoiceById(invoice.getId());

        //then
        assertEquals(Optional.of(invoice), actual);
        verify(database, times(1)).getInvoiceById(invoice.getId());
    }

    @Test
    void shouldReturnInvoicesInTimePeriod() {
        //given
        InvoiceService invoiceService = new InvoiceService(database);
        when(database.getInvoices(LocalDate.MIN, LocalDate.MAX)).thenReturn(List.of(invoice));

        //when
        Collection<Invoice> actual = invoiceService.getInvoices(LocalDate.MIN, LocalDate.MAX);

        //then
        assertEquals(List.of(invoice), actual);
        verify(database, times(1)).getInvoices(LocalDate.MIN, LocalDate.MAX);
    }

    @Test
    void shouldReturnAllInvoices() {
        //given
        InvoiceService invoiceService = new InvoiceService(database);

        //when
        when(invoiceService.getAllInvoices()).thenReturn(Collections.emptyList());

        //then
        assertEquals(invoiceService.getAllInvoices(), Collections.emptyList());
        verify(database, times(1)).getInvoices();
    }

    @Test
    void updateInvoice() {
        //given
        InvoiceService invoiceService = new InvoiceService(database);
        doNothing().when(database).updateInvoice(invoice.getId(), updatedInvoice);

        //when
        invoiceService.updateInvoice(invoice.getId(), updatedInvoice);

        //then
        verify(database, times(1)).updateInvoice(invoice.getId(), updatedInvoice);
    }

    @Test
    void deleteInvoice() {
        //given
        InvoiceService invoiceService = new InvoiceService(database);
        doNothing().when(database).deleteInvoice(invoice.getId());

        //when
        invoiceService.deleteInvoice(invoice.getId());

        //then
        verify(database, times(1)).deleteInvoice(invoice.getId());
    }
}
