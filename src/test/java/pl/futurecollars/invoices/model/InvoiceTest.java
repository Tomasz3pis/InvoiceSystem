package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    void shouldConstructInvoiceGivenArguments(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice invoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate)
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();

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
        Invoice invoice = new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2019, 12, 31))
                .setSaleDate(LocalDate.of(2019, 12, 31))
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();

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

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnTrueGivenEqualInvoices(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice firstInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate)
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();
        Invoice secondInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate)
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();

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
        Invoice firstInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2020, 1, 2))
                .setSaleDate(LocalDate.of(2020, 1, 1))
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();
        Invoice secondInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate)
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();
        Invoice thirdInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2020, 1, 2))
                .setSaleDate(LocalDate.of(2020, 1, 1))
                .setSeller(secondSeller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();
        Invoice fourthInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2020, 1, 2))
                .setSaleDate(LocalDate.of(2020, 1, 1))
                .setSeller(seller)
                .setBuyer(secondBuyer)
                .setEntries(entries)
                .build();
        Invoice fifthInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(LocalDate.of(2020, 1, 2))
                .setSaleDate(LocalDate.of(2020, 1, 1))
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(secondEntries)
                .build();

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
        Invoice invoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate)
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();
        Invoice secondInvoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate.minusDays(1))
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();

        // Then
        assertNotEquals(0, invoice.hashCode());
        assertNotEquals(invoice.hashCode(), secondInvoice.hashCode());
    }

    @ParameterizedTest
    @MethodSource("invoiceConstructorArguments")
    void shouldReturnStringGivenInvoice(LocalDate issueDate, LocalDate saleDate) {
        // Given

        // When
        Invoice invoice = new Invoice.InvoiceBuilder()
                .setIssueDate(issueDate)
                .setSaleDate(saleDate)
                .setSeller(seller)
                .setBuyer(buyer)
                .setEntries(entries)
                .build();

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
