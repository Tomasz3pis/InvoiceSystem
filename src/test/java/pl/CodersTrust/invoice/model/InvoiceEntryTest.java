package pl.coderstrust.invoice.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InvoiceEntryTest {

    @Test
    void shouldReturnSetDescription() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        entry.setDescription("New test descri");

        //then
        assertEquals(entry.getDescription(), "New test descri");
    }

    @Test
    void shouldReturnSetValue() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        entry.setValue(new BigDecimal(100L));

        //then
        assertEquals(entry.getValue(), new BigDecimal(100));
    }


    @Test
    void shouldReturnSetVatRate() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        entry.setVatRate(Vat.VAT_8);

        //then
        assertEquals(entry.getVatRate(), Vat.VAT_8);
    }

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        InvoiceEntry entry2 = new InvoiceEntry("", new BigDecimal(8), Vat.VAT_0);

        //then
        assertNotEquals(entry.hashCode(), entry2.hashCode());

    }

    @Test
    void shouldCheckIfToStringIsNotEmptyOrNull() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        String actual = entry.toString();

        //then
        assertNotEquals(null, actual);
        assertNotEquals("", actual);

    }

    @Test
    void shouldReturnHashCode() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        int actual = entry.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(entry));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldVerifyIfEqualWorksCorrectly(InvoiceEntry entry, InvoiceEntry otherEntry, boolean expected) {

        assertEquals(expected, entry.equals(otherEntry));
    }

    private static Stream<Arguments> dataProvider() {
        InvoiceEntry entry1 = new InvoiceEntry("position", new BigDecimal(235), Vat.VAT_23);
        InvoiceEntry entry2 = new InvoiceEntry("tests", new BigDecimal(88), Vat.VAT_8);
        InvoiceEntry entry3 = new InvoiceEntry("tests", new BigDecimal(88), Vat.VAT_8);

        return Stream.of(
                Arguments.of(entry1, entry2, false),
                Arguments.of(entry2, entry3, true),
                Arguments.of(entry1, null, false)
        );
    }
}
