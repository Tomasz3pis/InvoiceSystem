package pl.futurecollars.invoices;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.service.InvoiceService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InvoiceSystemIntegrationTest {

    @Autowired
    private Database database;
    @Autowired
    private InvoiceService invoiceService;

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldSaveGivenInvoices(Invoice firstInvoice, Invoice secondInvoice) {
        // Given

        // When
        invoiceService.saveInvoice(firstInvoice);
        invoiceService.saveInvoice(secondInvoice);

        // Then
        assertThat(invoiceService.getInvoice(firstInvoice.getId()), is(Optional.of(firstInvoice)));
        assertThat(invoiceService.getInvoice(secondInvoice.getId()), is(Optional.of(secondInvoice)));
    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldModifyGivenInvoice(
            Invoice firstInvoice, Invoice secondInvoice, Invoice thirdInvoice, Invoice fourthInvoice) {
        // Given
        invoiceService.saveInvoice(thirdInvoice);
        invoiceService.saveInvoice(fourthInvoice);

        // When
        fourthInvoice.setSaleDate(LocalDate.of(2020, 2, 1));
        invoiceService.updateInvoice(fourthInvoice.getId(), fourthInvoice);

        // Then
        assertThat(invoiceService
                        .getInvoice(fourthInvoice.getId())
                        .get()
                        .getSaleDate(),
                is(LocalDate.of(2020, 2, 1)));
    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldRemoveGivenInvoice(Invoice fifthInvoice, Invoice sixthInvoice) {
        // Given
        invoiceService.saveInvoice(fifthInvoice);
        invoiceService.saveInvoice(sixthInvoice);
        assertThat(database.getInvoiceById(fifthInvoice.getId()), is(Optional.of(fifthInvoice)));
        int expectedInvoiceCount = invoiceService.getInvoices().size() - 1;

        // When
        invoiceService.deleteInvoice(fifthInvoice.getId());

        // Then
        assertThat(invoiceService.getInvoices().size(), is(expectedInvoiceCount));
        assertThat(database.getInvoiceById(fifthInvoice.getId()), is(Optional.empty()));
        assertThat(invoiceService.getInvoice(fifthInvoice.getId()), is(Optional.empty()));
    }

    private static Stream<Arguments> invoiceSystemTestArguments() {
        return Stream.of(
                Arguments.of(getInvoice(1), getInvoice(2), getInvoice(3), getInvoice(4, 4))
        );
    }
}
