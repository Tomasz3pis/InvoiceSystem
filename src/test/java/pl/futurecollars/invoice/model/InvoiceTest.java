package pl.futurecollars.invoice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import pl.futurecollars.invoice.TestInvoiceProvider;

import java.math.BigDecimal;

class InvoiceTest {

    private TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();

    @Test
    void shouldReturnTotalValueWithVat() {
        //given
        Invoice invoice = testInvoiceProvider.getBaseInvoice();
        Invoice secondInvoice = testInvoiceProvider.getInvoiceWith5Entries();

        //when
        BigDecimal totalValueWithVat = invoice.getTotalValueWithVat();
        BigDecimal secondTotalValueWithVat = secondInvoice.getTotalValueWithVat();

        //then
        assertEquals(totalValueWithVat, new BigDecimal("10521.00"));
        assertEquals(secondTotalValueWithVat, new BigDecimal("12568.50"));
    }

    @Test
    void shouldReturnTotalValueWithoutVat() {
        //given
        Invoice invoice = testInvoiceProvider.getBaseInvoice();
        Invoice secondInvoice = testInvoiceProvider.getInvoiceWith5Entries();

        //when
        BigDecimal totalValueWithVat = invoice.getTotalValueWithoutVat();
        BigDecimal secondTotalValueWithVat = secondInvoice.getTotalValueWithoutVat();

        //then
        assertEquals(totalValueWithVat, new BigDecimal("8700"));
        assertEquals(secondTotalValueWithVat, new BigDecimal("12150"));
    }

    @Test
    void shouldCheckIfEqualsAndHashcodeIsWorking() {
        //given

        //when

        //then
        EqualsVerifier.forClass(Invoice.class)
                .verify();
    }

    @Test
    void shouldCheckIfToStringIsWorking() {
        //given

        //when

        //then
        ToStringVerifier.forClass(Invoice.class)
                .verify();
    }
}
