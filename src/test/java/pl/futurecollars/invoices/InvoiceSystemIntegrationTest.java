package pl.futurecollars.invoices;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_8;
import static pl.futurecollars.invoices.model.Vat.VAT_ZW;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.database.InMemoryDatabase;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.InvoiceEntry;
import pl.futurecollars.invoices.model.PostalAddress;
import pl.futurecollars.invoices.service.IdService;
import pl.futurecollars.invoices.service.InvoiceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class InvoiceSystemIntegrationTest {

    private static Database database = new InMemoryDatabase();
    private static IdService idService = new IdService(database);
    private static InvoiceService invoiceService = new InvoiceService(database);

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldSaveGivenInvoices(
            Invoice firstInvoice,
            Invoice secondInvoice) {

        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);

        assertThat(invoiceService.getInvoice(firstInvoice.getId()),
                is(firstInvoice));
        assertThat(invoiceService.getInvoice(secondInvoice.getId()),
                is(secondInvoice));
    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldModifyGivenInvoice(
            Invoice thirdInvoice,
            Invoice fourthInvoice) {

        invoiceService.saveInvoice(thirdInvoice);
        invoiceService.saveInvoice(fourthInvoice);
        fourthInvoice.setSaleDate(LocalDate.of(2020, 2, 1));
        invoiceService.updateInvoice(fourthInvoice);

        assertThat(
                invoiceService.getInvoice(fourthInvoice.getId()).getSaleDate(),
                is(LocalDate.of(2020, 2, 1)));
    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldRemoveGivenInvoice(
            Invoice fifthInvoice,
            Invoice sixthInvoice) {

        invoiceService.saveInvoice(fifthInvoice);
        invoiceService.saveInvoice(sixthInvoice);
        invoiceService.deleteInvoice(fifthInvoice.getId());

        assertThat(invoiceService.getInvoices().size(), is(5));
        assertFalse(idService.isIdPresent(fifthInvoice.getId()));
        assertThrows(IllegalArgumentException.class,
                () -> invoiceService.getInvoice(fifthInvoice.getId()));
    }

    private static Stream<Arguments> invoiceSystemTestArguments() {

        PostalAddress sellerAddress = new PostalAddress(
                "Sprzedajna",
                "99A",
                "199",
                "99-999",
                "Sprzedajnice");
        Company seller = new Company(
                "1234567890",
                "John Doe Sales",
                sellerAddress);

        PostalAddress buyerAddress = new PostalAddress(
                "Pokupna",
                "123",
                "",
                "12-345",
                "Wola Kupczycka");
        Company buyer = new Company(
                "0123456789",
                "Jane Doe Buyers",
                buyerAddress);

        InvoiceEntry firstEntry = new InvoiceEntry(
                "Produkt1",
                5,
                BigDecimal.valueOf(14.99),
                VAT_23);
        InvoiceEntry secondEntry = new InvoiceEntry(
                "Produkt2",
                1,
                BigDecimal.valueOf(125),
                VAT_8);
        InvoiceEntry thirdEntry = new InvoiceEntry(
                "Testowy Produkt3",
                10,
                BigDecimal.valueOf(4.49),
                VAT_ZW);

        LocalDate firstInvoiceIssueDate = LocalDate.now().minusDays(10);

        List<InvoiceEntry> firstInvoiceEntries = new ArrayList<>();
        firstInvoiceEntries.add(firstEntry);
        firstInvoiceEntries.add(secondEntry);

        Invoice firstInvoice = new Invoice(
                idService.getNewId(firstInvoiceIssueDate),
                firstInvoiceIssueDate,
                seller,
                buyer,
                firstInvoiceEntries);

        LocalDate secondInvoiceIssueDate = LocalDate.now();
        LocalDate secondInvoiceSaleDate = LocalDate.now().minusDays(2);

        List<InvoiceEntry> secondInvoiceEntries = new ArrayList<>();
        secondInvoiceEntries.add(thirdEntry);

        Invoice secondInvoice = new Invoice(
                idService.getNewId(secondInvoiceSaleDate),
                secondInvoiceIssueDate,
                secondInvoiceSaleDate,
                seller,
                buyer,
                secondInvoiceEntries);
        return Stream.of(
                Arguments.of(firstInvoice, secondInvoice)
        );
    }
}
