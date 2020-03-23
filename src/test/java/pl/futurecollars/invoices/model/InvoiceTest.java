package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
    void shouldConstructInvoiceGivenArguments(LocalDate issueDate) {
        // Given

        // When
        Invoice invoice = new Invoice(issueDate, seller, buyer, entries);

        // Then
        assertThat(invoice.getIssueDate(), is(issueDate));
        assertThat(invoice.getSaleDate(), is(issueDate));
        assertThat(invoice.getSeller(), is(seller));
        assertThat(invoice.getBuyer(), is(buyer));
        assertThat(invoice.getEntries(), is(entries));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldConstructInvoiceGivenArgumentsIncludingSaleDate(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice invoice = new Invoice(issueDate, saleDate, seller, buyer, entries);

        // Then
        assertThat(invoice.getIssueDate(), is(issueDate));
        assertThat(invoice.getSaleDate(), is(saleDate));
        assertThat(invoice.getSeller(), is(seller));
        assertThat(invoice.getBuyer(), is(buyer));
        assertThat(invoice.getEntries(), is(entries));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldSetInvoiceFields(LocalDate issueDate, LocalDate saleDate) {
        // Given
        Invoice invoice = new Invoice(
                LocalDate.of(2019, 12, 31),
                LocalDate.of(2019, 12, 31),
                seller,
                buyer,
                entries);

        // When
        invoice.setId(1L);
        invoice.setIssueDate(issueDate);
        invoice.setSaleDate(saleDate);
        invoice.setSeller(secondSeller);
        invoice.setBuyer(secondBuyer);
        invoice.setEntries(secondEntries);

        // Then
        assertThat(invoice.getId(), is(1L));
        assertThat(invoice.getIssueDate(), is(issueDate));
        assertThat(invoice.getSaleDate(), is(saleDate));
        assertThat(invoice.getSeller(), is(secondSeller));
        assertThat(invoice.getBuyer(), is(secondBuyer));
        assertThat(invoice.getEntries(), is(secondEntries));
    }

    @Test
    void shouldThrowExceptionGivenNull() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(null, seller, buyer, entries));
        assertThat(exception.getMessage(), is(
                "Provided "
                        + "issueDate"
                        + " Object cannot be null"));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorNullArgumentsIncludingSaleDate")
    void shouldThrowExceptionGivenNullIncludingSaleDate(
            LocalDate issueDate, LocalDate saleDate, String nullObjectName) {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(issueDate, saleDate, seller, buyer, entries));
        assertThat(exception.getMessage(), is(
                "Provided "
                        + nullObjectName
                        + " Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullSeller() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(LocalDate.of(2020, 1, 2), null, buyer, entries));
        assertThat(exception.getMessage(), is("Provided seller Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullBuyer() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(LocalDate.of(2020, 1, 2), seller, null, entries));
        assertThat(exception.getMessage(), is("Provided buyer Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullEntries() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Invoice(LocalDate.of(2020, 1, 2), seller, buyer, null));
        assertThat(exception.getMessage(), is("Provided entries Object cannot be null"));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnTrueGivenEqualInvoices(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice firstInvoice = new Invoice(issueDate, saleDate, seller, buyer, entries);
        Invoice secondInvoice = new Invoice(issueDate, saleDate, seller, buyer, entries);

        // Then
        assertThat(firstInvoice.equals(secondInvoice), is(true));
        assertThat(firstInvoice.equals(firstInvoice), is(true));
        assertThat(secondInvoice.equals(secondInvoice), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseGivenDifferentInvoices(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice firstInvoice = new Invoice(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                buyer,
                entries);
        Invoice secondInvoice = new Invoice(
                issueDate,
                saleDate,
                seller,
                buyer,
                entries);
        Invoice thirdInvoice = new Invoice(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                secondSeller,
                buyer,
                entries);
        Invoice fourthInvoice = new Invoice(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                secondBuyer,
                entries);
        Invoice fifthInvoice = new Invoice(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 1),
                seller,
                buyer,
                secondEntries);

        // Then
        assertThat(firstInvoice.equals(secondInvoice), is(false));
        assertThat(firstInvoice.equals(thirdInvoice), is(false));
        assertThat(firstInvoice.equals(fourthInvoice), is(false));
        assertThat(firstInvoice.equals(fifthInvoice), is(false));
        assertThat(firstInvoice.equals("otherClassObject"), is(false));
        assertThat(firstInvoice.equals(null), is(false));
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnHashCodeGivenInvoice(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice invoice = new Invoice(issueDate, saleDate, seller, buyer, entries);
        Invoice secondInvoice = new Invoice(issueDate, saleDate.minusDays(1), seller, buyer, entries);

        // Then
        assertNotEquals(0, invoice.hashCode());
        assertNotEquals(invoice.hashCode(), secondInvoice.hashCode());
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnStringGivenInvoice(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice invoice = new Invoice(issueDate, saleDate, seller, buyer, entries);

        // Then
        assertNotEquals(null, invoice.toString());
        assertNotEquals("", invoice.toString());
    }

    private static Stream<Arguments> invoiceConstructorArguments() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2020, 1, 1)),
                Arguments.of(
                        LocalDate.of(2020, 2, 10),
                        LocalDate.of(2020, 1, 29)),
                Arguments.of(
                        LocalDate.of(2020, 3, 20),
                        LocalDate.of(2020, 3, 20))
        );
    }

    private static Stream<Arguments> invoiceConstructorNullArguments() {
        return Stream.of(
                Arguments.of(LocalDate.of(2020, 1, 2), "id"),
                Arguments.of(null, "issueDate"));
    }

    private static Stream<Arguments>
    invoiceConstructorNullArgumentsIncludingSaleDate() {
        return Stream.of(
                Arguments.of(null, LocalDate.of(2020, 1, 1), "issueDate"),
                Arguments.of(LocalDate.of(2020, 1, 2), null, "saleDate"));
    }

    private static Stream<Arguments> notEqualsArguments() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2099, 9, 9),
                        LocalDate.of(2020, 1, 1)),
                Arguments.of(
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2099, 9, 9))
        );
    }
}
