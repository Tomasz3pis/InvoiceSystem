package pl.futurecollars.invoice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class InvoiceEntryTest {

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        InvoiceEntry firstEntry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        InvoiceEntry secondEntry = new InvoiceEntry("", new BigDecimal(8), Vat.VAT_0, 1);

        //then
        assertNotEquals(firstEntry.hashCode(), secondEntry.hashCode());
    }

    @Test
    void shouldCheckIfToStringIsWorking() {
        //given

        //when

        //then
        ToStringVerifier.forClass(InvoiceEntry.class)
                .verify();
    }

    @Test
    void shouldReturnHashCode() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        int actual = entry.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(entry));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldVerifyIfEqualWorksCorrectly(InvoiceEntry entry, InvoiceEntry otherEntry, boolean expected) {
        //given

        //when
        boolean actual = entry.equals(otherEntry);

        //then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> dataProvider() {
        InvoiceEntry firstEntry = new InvoiceEntry("position", new BigDecimal(235), Vat.VAT_23, 1);
        InvoiceEntry secondEntry = new InvoiceEntry("tests", new BigDecimal(88), Vat.VAT_8, 1);
        InvoiceEntry sameAsSecondEntry = new InvoiceEntry("tests", new BigDecimal(88), Vat.VAT_8, 1);

        return Stream.of(
                Arguments.of(firstEntry, secondEntry, false),
                Arguments.of(secondEntry, sameAsSecondEntry, true),
                Arguments.of(firstEntry, null, false)
        );
    }
}
