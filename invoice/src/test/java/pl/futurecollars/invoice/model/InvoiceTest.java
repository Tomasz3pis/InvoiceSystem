package pl.futurecollars.invoice.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceTest {
    @Test
    public void shouldReturnTotalValue() {
        //given
        Invoice invoice = new Invoice(1L, LocalDate.now(), new Company("Firma", "551551"),
                new Company("Firma2", "123"),
                Arrays.asList(new InvoiceEntry("Cards", new BigDecimal("1000.50")),
                        new InvoiceEntry("Puzzles", new BigDecimal("9000.50"))));
        //when
        BigDecimal total = invoice.getTotalValue();
        //then
        assertEquals(new BigDecimal("10001.00"), total);
    }


}