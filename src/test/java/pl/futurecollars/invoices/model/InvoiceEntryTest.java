package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.model.Vat.VAT_0;
import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_8;
import static pl.futurecollars.invoices.model.Vat.VAT_ZW;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class InvoiceEntryTest {

    @ParameterizedTest
    @MethodSource("entryConstructorArguments")
    void shouldConstructEntryGivenArguments(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat) {
        InvoiceEntry entry = new InvoiceEntry(
                itemName, quantity, netPrice, vat);

        assertThat(entry.getItemName(), is(itemName));
        assertThat(entry.getQuantity(), is(quantity));
        assertThat(entry.getNetPrice(), is(netPrice));
        assertThat(entry.getVat(), is(vat));
    }

    @ParameterizedTest
    @MethodSource("entryConstructorArguments")
    void shouldSetEntryFields(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat) {

        InvoiceEntry entry = new InvoiceEntry(
                "someName", 0, BigDecimal.valueOf(0), VAT_0);

        entry.setItemName(itemName);
        entry.setQuantity(quantity);
        entry.setNetPrice(netPrice);
        entry.setVat(vat);

        assertThat(entry.getItemName(), is(itemName));
        assertThat(entry.getQuantity(), is(quantity));
        assertThat(entry.getNetPrice(), is(netPrice));
        assertThat(entry.getVat(), is(vat));
    }

    @ParameterizedTest
    @MethodSource("entryConstructorNullArguments")
    void shouldThrowExceptionGivenNull(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat,
            String nullObjectName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new InvoiceEntry(itemName, quantity, netPrice, vat));
        assertThat(exception.getMessage(), is(
                "Provided "
                        + nullObjectName
                        + " Object cannot be null"
        ));
    }

    @ParameterizedTest
    @MethodSource("entryConstructorArguments")
    void shouldReturnTrueGivenEqualEntries(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat) {

        InvoiceEntry firstEntry = new InvoiceEntry(
                itemName, quantity, netPrice, vat);
        InvoiceEntry secondEntry = new InvoiceEntry(
                itemName, quantity, netPrice, vat);

        assertThat(firstEntry.equals(secondEntry), is(true));
        assertThat(firstEntry.equals(firstEntry), is(true));
        assertThat(secondEntry.equals(secondEntry), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseGivenDifferentEntries(InvoiceEntry entryToCompare) {

        InvoiceEntry entry = new InvoiceEntry(
                "First Item Name",
                1,
                BigDecimal.valueOf(100),
                VAT_23);

        assertThat(entry.equals(entryToCompare), is(false));
        assertThat(entryToCompare.equals(null), is(false));
    }

    @ParameterizedTest
    @MethodSource("entryConstructorArguments")
    void shouldReturnHashCodeGivenEntry(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat) {
        InvoiceEntry entry = new InvoiceEntry(
                itemName, quantity, netPrice, vat);
        InvoiceEntry secondEntry = new InvoiceEntry(
                "someOtherName", quantity, netPrice, vat);

        assertNotEquals(0, entry.hashCode());
        assertNotEquals(entry.hashCode(), secondEntry.hashCode());
    }

    @ParameterizedTest
    @MethodSource("entryConstructorArguments")
    void shouldReturnStringGivenEntry(
            String itemName,
            int quantity,
            BigDecimal netPrice,
            Vat vat) {
        InvoiceEntry entry = new InvoiceEntry(
                itemName, quantity, netPrice, vat);

        assertNotEquals(null, entry.toString());
        assertNotEquals("", entry.toString());
    }

    private static Stream<Arguments> entryConstructorArguments() {
        return Stream.of(
                Arguments.of(
                        "First Item Name",
                        1,
                        BigDecimal.valueOf(100),
                        VAT_23
                ),
                Arguments.of(
                        "Second item name",
                        5,
                        BigDecimal.valueOf(14.99),
                        VAT_8
                ),
                Arguments.of(
                        "third item name with code 123456-345/2",
                        125,
                        BigDecimal.valueOf(3.2),
                        VAT_ZW
                )
        );
    }

    private static Stream<Arguments> entryConstructorNullArguments() {
        return Stream.of(
                Arguments.of(
                        null,
                        1,
                        BigDecimal.valueOf(100),
                        VAT_23,
                        "itemName"
                ),
                Arguments.of(
                        "First Item Name",
                        1,
                        null,
                        VAT_23,
                        "netPrice"
                ),
                Arguments.of(
                        "First Item Name",
                        1,
                        BigDecimal.valueOf(100),
                        null,
                        "vat"
                )
        );
    }

    private static Stream<Arguments> notEqualsArguments() {
        return Stream.of(
                Arguments.of(new InvoiceEntry(
                        "altered name",
                        1,
                        BigDecimal.valueOf(100),
                        VAT_23
                )),
                Arguments.of(new InvoiceEntry(
                        "First Item Name",
                        0,
                        BigDecimal.valueOf(100),
                        VAT_23
                )),
                Arguments.of(new InvoiceEntry(
                        "First Item Name",
                        1,
                        BigDecimal.valueOf(0),
                        VAT_23
                )),
                Arguments.of(new InvoiceEntry(
                        "First Item Name",
                        1,
                        BigDecimal.valueOf(100),
                        VAT_ZW
                ))
        );
    }
}
