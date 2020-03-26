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
    void shouldReturnSetDescription() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        entry.setDescription("New test descri");

        //then
        assertEquals(entry.getDescription(), "New test descri");
    }

    @Test
    void shouldReturnSetValue() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        entry.setUnitPrice(new BigDecimal(100L));

        //then
        assertEquals(entry.getUnitPrice(), new BigDecimal(100));
    }


    @Test
    void shouldReturnSetVatRate() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        entry.setVatRate(Vat.VAT_8);

        //then
        assertEquals(entry.getVatRate(), Vat.VAT_8);
    }

    @Test
    void shouldReturnSetUnitPrice() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        entry.setQuantity(5);

        //then
        assertEquals(entry.getQuantity(), 5);
    }

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0, 1);

        //when
        InvoiceEntry entry2 = new InvoiceEntry("", new BigDecimal(8), Vat.VAT_0, 1);

        //then
        assertNotEquals(entry.hashCode(), entry2.hashCode());
    }

    @Test
    void shouldCheckIfToStringIsNotEmptyOrNull() {
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
        InvoiceEntry entry1 = new InvoiceEntry("position", new BigDecimal(235), Vat.VAT_23, 1);
        InvoiceEntry entry2 = new InvoiceEntry("tests", new BigDecimal(88), Vat.VAT_8, 1);
        InvoiceEntry entry3 = new InvoiceEntry("tests", new BigDecimal(88), Vat.VAT_8, 1);

        return Stream.of(
                Arguments.of(entry1, entry2, false),
                Arguments.of(entry2, entry3, true),
                Arguments.of(entry1, null, false)
        );
    }
}
