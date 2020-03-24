package pl.coderstrust.invoice.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class InvoiceTest {

    Company company = mock(Company.class);
    InvoiceEntry entry = mock(InvoiceEntry.class);

    @Test
    void shouldReturnSetSeller() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        invoice.setSeller(company);

        //then
        assertEquals(invoice.getSeller(), company);
    }

    @Test
    void shouldReturnSetBuyer() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        invoice.setBuyer(company);

        //then
        assertEquals(invoice.getBuyer(), company);
    }

    @Test
    void shouldReturnSetEntries() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        invoice.setEntries(List.of(entry));

        //then
        assertEquals(invoice.getEntries(), List.of(entry));
    }

    @Test
    void shouldReturnSetDate() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        invoice.setData(LocalDate.of(2015, 12, 14));

        //then
        assertEquals(invoice.getData(), LocalDate.of(2015, 12, 14));
    }

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        Invoice invoice2 = new Invoice(null, null, LocalDate.now().minusDays(5), new ArrayList<>());

        //then
        assertNotEquals(invoice.hashCode(), invoice2.hashCode());
    }

    @Test
    void shouldCheckIfToStringIsNotEmptyOrNull() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        String actual = invoice.toString();

        //then
        assertNotEquals(null, actual);
        assertNotEquals("", actual);
    }

    @Test
    void shouldReturnHashCode() {
        //given
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());

        //when
        int actual = invoice.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(invoice));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldVerifyIfEqualWorksCorrectly(Invoice invoice, Invoice otherInvoice, boolean expected) {

        assertEquals(expected, invoice.equals(otherInvoice));
    }

    private static Stream<Arguments> dataProvider() {

        Invoice invoice1 = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice invoice2 = new Invoice(null, null, LocalDate.now().minusDays(5), new ArrayList<>());
        Invoice invoice3 = new Invoice(null, null, LocalDate.now().minusDays(5), new ArrayList<>());

        return Stream.of(
                Arguments.of(invoice1, invoice2, false),
                Arguments.of(invoice2, invoice3, true),
                Arguments.of(invoice1, null, false)
        );
    }
}
