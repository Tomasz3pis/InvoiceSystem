package pl.futurecollars.invoices.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class InMemoryDatabaseTest {

    @Mock
    private Invoice invoice;

    @Mock
    private Invoice secondInvoice;

    @Mock
    private Invoice thirdInvoice;

    @InjectMocks
    private InMemoryDatabase database;

    @Test
    void shouldSaveInvoice() {
        // Given
        long id = 1L;

        // When
        database.saveInvoice(invoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));
    }

    @Test
    void shouldSaveManyInvoices() {
        // Given
        long id = 1L;
        long secondId = 2L;
        long thirdId = 3L;

        // When
        database.saveInvoice(invoice);
        database.saveInvoice(secondInvoice);
        database.saveInvoice(thirdInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));
        assertThat(database.getInvoiceById(secondId), is(Optional.of(secondInvoice)));
        assertThat(database.getInvoiceById(thirdId), is(Optional.of(thirdInvoice)));
    }

    @ParameterizedTest
    @MethodSource("getInvoicesLocalDateArguments")
    void shouldGetInvoicesInGivenDateRange(
            List<LocalDate> issueDates,
            LocalDate startDate,
            LocalDate endDate,
            int expectedSize,
            LocalDate startDateToCompare,
            LocalDate endDateToCompare) {
        // Given
        database.saveInvoice(invoice);
        database.saveInvoice(secondInvoice);
        database.saveInvoice(thirdInvoice);

        when(invoice.getIssueDate()).thenReturn(issueDates.get(0));
        when(secondInvoice.getIssueDate()).thenReturn(issueDates.get(1));
        when(thirdInvoice.getIssueDate()).thenReturn(issueDates.get(2));

        // When
        List<Invoice> invoices = database.getInvoices(startDate, endDate);

        // Then
        assertThat(invoices.size(), is(expectedSize));
        for (int i = 0; i < expectedSize; i++) {
            assertTrue(invoices.get(i).getIssueDate().isAfter(startDateToCompare));
            assertTrue(invoices.get(i).getIssueDate().isBefore(endDateToCompare));
        }
    }

    @Test
    void shouldUpdateInvoiceGivenSameIdInvoice() {
        // Given
        long id = 1L;
        database.saveInvoice(invoice);

        // When
        database.updateInvoice(id, secondInvoice);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.of(secondInvoice)));
    }

    @Test
    void updateInvoiceShouldThrowExceptionGivenNotPresentInvoice() {
        // Given
        long id = 1L;

        // When

        // Then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.updateInvoice(id, invoice));
        assertThat(exception.getMessage(), is(
                "Invoice with provided id does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }

    @Test
    void shouldDeleteInvoiceGivenId() {
        // Given
        long id = 1L;
        database.saveInvoice(invoice);
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));

        // When
        database.deleteInvoice(id);

        // Then
        assertThat(database.getInvoiceById(id), is(Optional.empty()));
    }

    @Test
    void deleteInvoiceShouldThrowExceptionGivenNotExistingId() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.deleteInvoice(1L));
        assertThat(exception.getMessage(), is(
                "Invoice with provided id does not exist in database. "
                        + "Invoice id: " + "1" + " not found."));
    }

    private static Stream<Arguments> getInvoicesLocalDateArguments() {
        return Stream.of(
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3)
                        )),
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 3, 3),
                        3,
                        LocalDate.of(2019, 12, 31),
                        LocalDate.of(2020, 3, 4)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2019, 1, 1),
                                LocalDate.of(2019, 2, 2),
                                LocalDate.of(2019, 3, 3))),
                        LocalDate.of(2018, 12, 15),
                        LocalDate.of(2020, 1, 1),
                        3,
                        LocalDate.of(2018, 12, 14),
                        LocalDate.of(2020, 1, 2)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2020, 2, 28),
                        1,
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 2, 29)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2020, 1, 2),
                        LocalDate.of(2020, 3, 4),
                        2,
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 3, 5)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2020, 2, 3),
                        LocalDate.of(2020, 4, 4),
                        1,
                        LocalDate.of(2020, 2, 2),
                        LocalDate.of(2020, 4, 5)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2019, 1, 1),
                        LocalDate.of(2020, 2, 1),
                        1,
                        LocalDate.of(2018, 12, 31),
                        LocalDate.of(2020, 2, 2)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2019, 1, 1),
                        LocalDate.of(2020, 2, 3),
                        2,
                        LocalDate.of(2018, 12, 31),
                        LocalDate.of(2020, 2, 4)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        null,
                        LocalDate.of(2021, 1, 1),
                        3,
                        LocalDate.MIN,
                        LocalDate.of(2021, 1, 2)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2010, 1, 1),
                        null,
                        3,
                        LocalDate.of(2009, 12, 31),
                        LocalDate.MAX),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        null,
                        null,
                        3,
                        LocalDate.MIN,
                        LocalDate.MAX),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2020, 4, 4),
                        LocalDate.of(2020, 6, 6),
                        0,
                        LocalDate.of(2020, 4, 3),
                        LocalDate.of(2020, 6, 7)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2019, 1, 1),
                        LocalDate.of(2019, 3, 3),
                        0,
                        LocalDate.of(2018, 12, 31),
                        LocalDate.of(2019, 3, 4)),
                Arguments.of(
                        new ArrayList<>(Arrays.asList(
                                LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 2),
                                LocalDate.of(2020, 3, 3))),
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2019, 3, 3),
                        0,
                        LocalDate.of(2019, 12, 31),
                        LocalDate.of(2019, 3, 4))
        );
    }
}
