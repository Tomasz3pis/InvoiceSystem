package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.model.Invoice.HASH_OFFSET;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class InvoiceTest {

    @Mock
    private Company buyer;

    @Mock
    private Company secondBuyer;

    @Mock
    private Company seller;

    @Mock
    private Company secondSeller;

    @Mock
    private List<InvoiceEntry> entries;

    @Mock
    private List<InvoiceEntry> secondEntries;

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldConstructInvoiceGivenArguments(String id, LocalDate issueDate) {

        Invoice invoice = new Invoice(
                id,
                issueDate,
                seller,
                buyer,
                entries);

        assertThat(invoice.getId(), is(id));
        assertThat(invoice.getIssueDate(), is(issueDate));
        assertThat(invoice.getSaleDate(), is(issueDate));
        assertThat(invoice.getSeller(), is(seller));
        assertThat(invoice.getBuyer(), is(buyer));
        assertThat(invoice.getEntries(), is(entries));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldConstructInvoiceGivenArgumentsIncludingSaleDate(
            String id, LocalDate issueDate, LocalDate saleDate) {

        Invoice invoice = new Invoice(
                id,
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);

        assertThat(invoice.getId(), is(id));
        assertThat(invoice.getIssueDate(), is(issueDate));
        assertThat(invoice.getSaleDate(), is(saleDate));
        assertThat(invoice.getSeller(), is(seller));
        assertThat(invoice.getBuyer(), is(buyer));
        assertThat(invoice.getEntries(), is(entries));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldSetInvoiceFields(
            String id, LocalDate issueDate, LocalDate saleDate) {

        Invoice invoice = new Invoice(
                id,
                LocalDate.of(2019, 12, 31),
                LocalDate.of(2019, 12, 31),
                seller,
                buyer,
                entries);

        invoice.setIssueDate(issueDate);
        invoice.setSaleDate(saleDate);
        invoice.setSeller(secondSeller);
        invoice.setBuyer(secondBuyer);
        invoice.setEntries(secondEntries);

        assertThat(invoice.getIssueDate(), is(issueDate));
        assertThat(invoice.getSaleDate(), is(saleDate));
        assertThat(invoice.getSeller(), is(secondSeller));
        assertThat(invoice.getBuyer(), is(secondBuyer));
        assertThat(invoice.getEntries(), is(secondEntries));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorNullArguments")
    void shouldThrowExceptionGivenNull(
            String id, LocalDate issueDate, String nullObjectName) {

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(
                        id,
                        issueDate,
                        seller,
                        buyer,
                        entries));
        assertThat(exception.getMessage(), is(
                "Provided "
                        + nullObjectName
                        + " Object cannot be null"));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorNullArgumentsIncludingSaleDate")
    void shouldThrowExceptionGivenNullIncludingSaleDate(
            String id,
            LocalDate issueDate,
            LocalDate saleDate,
            String nullObjectName) {

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(
                        id,
                        issueDate,
                        saleDate,
                        seller,
                        buyer,
                        entries));
        assertThat(exception.getMessage(), is(
                "Provided "
                        + nullObjectName
                        + " Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullSeller() {

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(
                        "20200101_0010",
                        LocalDate.of(2020, 1, 2),
                        null,
                        buyer,
                        entries
                )
        );

        assertThat(exception.getMessage(), is(
                "Provided seller Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullBuyer() {

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(
                        "20200101_0010",
                        LocalDate.of(2020, 1, 2),
                        seller,
                        null,
                        entries
                )
        );

        assertThat(exception.getMessage(), is(
                "Provided buyer Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullEntries() {

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(
                        "20200101_0010",
                        LocalDate.of(2020, 1, 2),
                        seller,
                        buyer,
                        null
                )
        );

        assertThat(exception.getMessage(), is(
                "Provided entries Object cannot be null"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "0",
            "12345678_0000",
            "19990101_0001",
            "20201301_0001",
            "20200132_0001",
            "20200101_001"
    })
    void shouldThrowExceptionGivenInvalidId(
            String id) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(
                        id,
                        LocalDate.of(2020, 1, 2),
                        seller,
                        buyer,
                        entries
                )
        );

        assertThat(exception.getMessage(), is("Provided id String: "
                + id + " is not a valid id format"));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnTrueGivenEqualInvoices(
            String id, LocalDate issueDate, LocalDate saleDate) {

        Invoice firstinvoice = new Invoice(
                id,
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);
        Invoice secondInvoice = new Invoice(
                id,
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);

        assertThat(firstinvoice.equals(secondInvoice), is(true));
        assertThat(firstinvoice.equals(firstinvoice), is(true));
        assertThat(secondInvoice.equals(secondInvoice), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseGivenDifferentInvoices(
            String id, LocalDate issueDate, LocalDate saleDate) {

        Invoice firstInvoice = new Invoice(
                "20200102_0001",
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                buyer,
                entries);
        Invoice secondInvoice = new Invoice(
                id,
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);
        Invoice thirdInvoice = new Invoice(
                "20200102_0001",
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                secondSeller,
                buyer,
                entries);
        Invoice fourthInvoice = new Invoice(
                "20200102_0001",
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                secondBuyer,
                entries);
        Invoice fifthInvoice = new Invoice(
                "20200102_0001",
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                buyer,
                secondEntries);

        assertThat(firstInvoice.equals(secondInvoice), is(false));
        assertThat(firstInvoice.equals(thirdInvoice), is(false));
        assertThat(firstInvoice.equals(fourthInvoice), is(false));
        assertThat(firstInvoice.equals(fifthInvoice), is(false));
        assertThat(firstInvoice.equals("otherClassObject"), is(false));
        assertThat(firstInvoice.equals(null), is(false));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnHashCodeGivenInvoice(
            String id, LocalDate issueDate, LocalDate saleDate) {

        Invoice invoice = new Invoice(
                id,
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);

        int expectedHashCode = (((((id.hashCode() * HASH_OFFSET)
                + issueDate.hashCode()) * HASH_OFFSET
                + saleDate.hashCode()) * HASH_OFFSET
                + seller.hashCode()) * HASH_OFFSET
                + buyer.hashCode()) * HASH_OFFSET
                + entries.hashCode();

        assertThat(invoice.hashCode(), is(expectedHashCode));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnStringGivenInvoice(
            String id, LocalDate issueDate, LocalDate saleDate) {

        Invoice invoice = new Invoice(
                id,
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);

        String expected = "\nInvoice{\n"
                + "id        = " + id + ",\n"
                + "issueDate = " + issueDate + ",\n"
                + "saleDate  = " + saleDate + ",\n"
                + "seller    = " + seller + ",\n"
                + "buyer     = " + buyer + ",\n"
                + "entries   = " + entries + "\n"
                + '}';

        assertThat(invoice.toString(), is(expected));
    }

    private static Stream<Arguments> invoiceConstructorArguments() {
        return Stream.of(
                Arguments.of(
                        "20200102_0001",
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2020, 1, 1)),
                Arguments.of(
                        "20200102_0001",
                        LocalDate.of(2020, 2, 10),
                        LocalDate.of(2020, 1, 29)),
                Arguments.of(
                        "20200102_0001",
                        LocalDate.of(2020, 3, 20),
                        LocalDate.of(2020, 3, 20))
        );
    }

    private static Stream<Arguments> invoiceConstructorNullArguments() {
        return Stream.of(
                Arguments.of(
                        null,
                        LocalDate.of(2020, 1, 2),
                        "id"),
                Arguments.of(
                        "20200102_0001",
                        null,
                        "issueDate")
        );
    }

    private static Stream<Arguments>
    invoiceConstructorNullArgumentsIncludingSaleDate() {
        return Stream.of(
                Arguments.of(
                        null,
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2020, 1, 1),
                        "id"),
                Arguments.of(
                        "20200102_0001",
                        null,
                        LocalDate.of(2020, 1, 1),
                        "issueDate"),
                Arguments.of(
                        "20200102_0001",
                        LocalDate.of(2020, 1, 2),
                        null,
                        "saleDate")
        );
    }

    private static Stream<Arguments> notEqualsArguments() {
        return Stream.of(
                Arguments.of(
                        "20200102_9999",
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2020, 1, 1)),
                Arguments.of(
                        "20200102_0001",
                        LocalDate.of(2099, 9, 9),
                        LocalDate.of(2020, 1, 1)),
                Arguments.of(
                        "20200102_0001",
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2099, 9, 9))
        );
    }
}
