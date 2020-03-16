package pl.futurecollars.invoice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoice.database.InMemoryInvoiceDatabase;
import pl.futurecollars.invoice.dto.InvoiceDTO;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceEntry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InMemoryInvoiceDatabase inMemoryInvoiceDatabase;

    @Mock
    private InvoiceTransformer invoiceTransformer;


    @Test
    void shouldSaveInvoice_whenDTOCorrect() {
        // given
        InvoiceDTO invoiceDTO = new InvoiceDTO(12L, LocalDate.now(), 50L, 150L, Collections.singletonList(new InvoiceEntry("aaa", new BigDecimal("23.23"))));
        Company seller = new Company("Firma", "551551");
        seller.setId(invoiceDTO.getSeller());
        Company buyer = new Company("Firma", "551551");
        buyer.setId(invoiceDTO.getBuyer());
        Invoice invoice = new Invoice(invoiceDTO.getId(), invoiceDTO.getDate(), seller, buyer, Collections.singletonList(new InvoiceEntry("aaa", new BigDecimal("23.23"))));
        when(invoiceTransformer.fromDto(invoiceDTO)).thenReturn(invoice);
        when(inMemoryInvoiceDatabase.save(invoice)).thenReturn(invoice);

        // when
        Invoice result = invoiceService.saveInvoice(invoiceDTO);

        // then
        assertEquals(result, invoice);
        assertEquals(result.getBuyer().getId(), invoiceDTO.getBuyer());
        assertEquals(result.getSeller().getId(), invoiceDTO.getSeller());
    }

    @Test
    void shouldGetInvoices() {
        // given

        Invoice invoice = new Invoice(123L, LocalDate.now(), new Company(), new Company(), Collections.singletonList(new InvoiceEntry()));
        Invoice invoice2 = new Invoice(254L, LocalDate.now(), new Company(), new Company(), Collections.singletonList(new InvoiceEntry()));
        when(inMemoryInvoiceDatabase.getAll()).thenReturn(List.of(invoice, invoice2));

        // when
        List<Invoice> result = invoiceService.getInvoices();

        // then
        assertThat(result).containsExactly(invoice, invoice2);
    }

    @Test
    void shouldGetInvoice() {
        //given
        Invoice invoice = new Invoice(123L, LocalDate.now(), new Company(), new Company(), Collections.singletonList(new InvoiceEntry()));
        when(inMemoryInvoiceDatabase.getById(125L)).thenReturn(invoice);

        //when
        Invoice result = invoiceService.getInvoice(125L);

        //then
        assertEquals(result, invoice);
    }

    @Test
    void shouldUpdateInvoice() {
        //given
        InvoiceDTO invoiceDTO = new InvoiceDTO(125L, LocalDate.now(), 50L, 150L, Collections.singletonList(new InvoiceEntry("aaa", new BigDecimal("23.23"))));
        Invoice invoice = new Invoice(125L, LocalDate.now(), new Company(), new Company(), Collections.singletonList(new InvoiceEntry()));
        when(invoiceTransformer.fromDto(invoiceDTO)).thenReturn(invoice);
        when(inMemoryInvoiceDatabase.update(invoice)).thenReturn(invoice);

        //when
        Invoice result = invoiceService.updateInvoice(invoiceDTO);

        //then
        assertEquals(result, invoice);
    }

    @Test
    void shouldDeleteInvoice() {
        //given
        //when
        invoiceService.deleteInvoice(123L);

        //then
        verify(inMemoryInvoiceDatabase).delete(123L);
    }
}