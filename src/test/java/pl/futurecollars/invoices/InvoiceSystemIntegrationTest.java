package pl.futurecollars.invoices;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pl.futurecollars.invoices.TestInvoiceProvider.getInvoice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.database.InMemoryDatabase;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.service.InvoiceService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

class InvoiceSystemIntegrationTest {

    private static Database database = new InMemoryDatabase();
    private static InvoiceService invoiceService = new InvoiceService(database);

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldSaveGivenInvoices(
            Invoice firstInvoice,
            Invoice secondInvoice) {

        System.out.println(firstInvoice);
        System.out.println(secondInvoice);

        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);

        assertThat(invoiceService.getInvoice(firstInvoice.getId()),
                is(Optional.of(firstInvoice)));
        assertThat(invoiceService.getInvoice(secondInvoice.getId()),
                is(Optional.of(secondInvoice)));
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
                invoiceService
                        .getInvoice(fourthInvoice.getId())
                        .get()
                        .getSaleDate(),
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
        assertFalse(database.getInvoices().containsKey(fifthInvoice.getId()));
        assertThat(invoiceService.getInvoice(fifthInvoice.getId()),
                is(Optional.empty()));
    }

    private static Stream<Arguments> invoiceSystemTestArguments() {
        return Stream.of(
                Arguments.of(
                        getInvoice(1234567, 20),
                        getInvoice(2, 2)
                )
        );
    }
}
