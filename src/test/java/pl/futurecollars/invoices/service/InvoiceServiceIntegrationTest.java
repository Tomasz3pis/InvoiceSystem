package pl.futurecollars.invoices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.futurecollars.invoices.database.Database;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceOne;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceThree;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceTwo;

@SpringBootTest
@ContextConfiguration(initializers = TemporaryFolderInitializer.class)
class InvoiceServiceIntegrationTest {

    @Autowired
    private Database database;

    @Autowired
    private InvoiceService invoiceService;

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldSaveGivenInvoices(Invoice firstInvoice, Invoice secondInvoice) throws JsonProcessingException {
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
            Invoice firstInvoice, Invoice secondInvoice, Invoice thirdInvoice, Invoice fourthInvoice)
            throws JsonProcessingException {
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
    void shouldRemoveGivenInvoice(Invoice fifthInvoice, Invoice sixthInvoice) throws JsonProcessingException {
        // Given
        invoiceService.saveInvoice(fifthInvoice);
        invoiceService.saveInvoice(sixthInvoice);
        assertThat(database.getInvoiceById(fifthInvoice.getId()), is(Optional.of(fifthInvoice)));
        List<Invoice> xd = database.getInvoices();
        int expectedInvoiceCount = invoiceService.getInvoices().size() - 1;

        // When
        invoiceService.deleteInvoice(fifthInvoice.getId());

        // Then
        assertThat(invoiceService.getInvoices().size(), is(expectedInvoiceCount));
        assertThat(database.getInvoiceById(fifthInvoice.getId()), is(Optional.empty()));
        assertThat(invoiceService.getInvoice(fifthInvoice.getId()), is(Optional.empty()));
    }

    private static Stream<Arguments> invoiceSystemTestArguments() {
        Invoice invoiceFour = getInvoiceOne();
        invoiceFour.setSaleDate(LocalDate.of(2020, 3, 3));
        return Stream.of(
                Arguments.of(getInvoiceOne(), getInvoiceTwo(), getInvoiceThree(), invoiceFour)
        );
    }
}
