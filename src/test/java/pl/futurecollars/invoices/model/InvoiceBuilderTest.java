package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBoughtt;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuySome;
import static pl.futurecollars.invoices.providers.TestEntriesProvider.invoiceEntriesCountOne;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.futurecollars.invoices.exceptions.InvoiceNotCompleteException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

class InvoiceBuilderTest {

    private Company buyer = companyBuySome();
    private Company seller = companyBoughtt();
    private List<InvoiceEntry> entries = invoiceEntriesCountOne();

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

    @Test
    void builderShouldThrowExceptionWhenMissingFields() {
        Exception exception = assertThrows(InvoiceNotCompleteException.class, () ->
                new Invoice.InvoiceBuilder().build());
        assertThat(exception.getMessage(), containsString(
                "issueDate saleDate seller buyer entries"));
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
}
