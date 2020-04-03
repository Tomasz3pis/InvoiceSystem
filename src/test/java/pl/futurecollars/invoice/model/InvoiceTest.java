package pl.futurecollars.invoice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.futurecollars.invoice.TestInvoiceProvider;

import java.math.BigDecimal;
import java.util.stream.Stream;

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
    void shouldCompareTwoDifferentHashCodes() {
        //given
        Invoice invoice = testInvoiceProvider.getBaseInvoice();

        //when
        Invoice secondInvoice = testInvoiceProvider.getInvoiceWith5Entries();

        //then
        assertNotEquals(invoice.hashCode(), secondInvoice.hashCode());
    }

    @Test
    void shouldCheckIfToStringIsWorking() {
        //given

        //when

        //then
        ToStringVerifier.forClass(Invoice.class)
                .verify();
    }

    @Test
    void shouldReturnHashCode() {
        //given
        Invoice invoice = testInvoiceProvider.getBaseInvoice();

        //when
        int actual = invoice.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(invoice));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldVerifyIfEqualWorksCorrectly(Invoice invoice, Invoice otherInvoice, boolean expected) {
        //given

        //when
        boolean actual = invoice.equals(otherInvoice);

        //then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> dataProvider() {

        TestInvoiceProvider testInvoiceProvider = new TestInvoiceProvider();
        Invoice firstInvoice = testInvoiceProvider.getInvoiceWith5Entries();
        Invoice thirdInvoice = testInvoiceProvider.getBaseInvoice();
        Invoice secondInvoice = testInvoiceProvider.getSameAsBaseInvoice();

        return Stream.of(
                Arguments.of(firstInvoice, secondInvoice, false),
                Arguments.of(secondInvoice, thirdInvoice, true),
                Arguments.of(firstInvoice, null, false)
        );
    }
}
