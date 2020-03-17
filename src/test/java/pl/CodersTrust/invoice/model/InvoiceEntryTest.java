package pl.CodersTrust.invoice.model;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;


class InvoiceEntryTest {


    @ParameterizedTest
    @MethodSource("entriesProvider")
    void shouldReturnSetDescription(InvoiceEntry entry) {

        entry.setDescription("New test descri");

        Assert.assertThat(entry.getDescription(), is("New test descri"));
    }


    @ParameterizedTest
    @MethodSource("entriesProvider")
    void shouldReturnSetValue(InvoiceEntry entry) {

        entry.setValue(new BigDecimal(100L));

        Assert.assertThat(entry.getValue(), is(new BigDecimal(100)));
    }


    @ParameterizedTest
    @MethodSource("entriesProvider")
    void shouldReturnSetVatRate(InvoiceEntry entry) {

        entry.setVatRate(Vat.VAT_8);

        Assert.assertThat(entry.getVatRate(), is(Vat.VAT_8));
    }

    @ParameterizedTest
    @MethodSource("entriesProvider")
    void shouldReturnFalseWhenArgIsNull(InvoiceEntry entry) {

        Assert.assertFalse(entry.equals(null));

    }

    private static Stream<Arguments> entriesProvider() {
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);
        InvoiceEntry entry2 = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        return Stream.of(Arguments.of(entry, entry2));
    }
}