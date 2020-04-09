package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBoughtt;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuySome;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyGiftShops;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountOne;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountThree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

class InvoiceTest {

    private Company buyer = companyBuySome();
    private Company secondBuyer = companyBuyMore();
    private Company seller = companyBoughtt();
    private Company secondSeller = companyGiftShops();
    private List<InvoiceEntry> entries = invoiceEntriesCountOne();
    private List<InvoiceEntry> secondEntries = invoiceEntriesCountThree();

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
