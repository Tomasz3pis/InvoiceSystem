package pl.futurecollars.invoices.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.database.Database;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class IdServiceTest {

    @Mock
    private Database database;

    @Test
    void shouldConstructIdServiceGivenDatabase() {
        // Given
        String id = "20200101_0001";
        List<String> expectedIdNumbers = new LinkedList<>();
        expectedIdNumbers.add(id);
        when(database.getIdNumbers()).thenReturn(expectedIdNumbers);
        when(database.getLastId()).thenReturn(id);

        // When
        IdService idService = new IdService(database);

        // Then
        assertThat(idService.getIdNumbers(), is(expectedIdNumbers));
    }

    @Test
    void shouldConstructIdServiceGivenEmptyDatabase() {
        // Given
        String id = "20200101_0001";
        List<String> expectedIdNumbers = new LinkedList<>();
        expectedIdNumbers.add(id);
        when(database.getIdNumbers()).thenReturn(expectedIdNumbers);
        when(database.getLastId()).thenReturn(null);

        // When
        IdService idService = new IdService(database);

        // Then
        assertThat(idService.getIdNumbers(), is(expectedIdNumbers));
    }

    @Test
    void shouldReturnNewGeneratedIdGivenDate() {
        // Given
        LocalDate saleDate = LocalDate.of(2020, 1, 1);
        IdService idService = new IdService(database);

        // When
        String actual = idService.getNewId(saleDate);

        // Then
        assertThat(actual, is("20200101_0001"));
    }

    @ParameterizedTest
    @MethodSource("getNewIdArguments")
    void shouldReturnNextGeneratedIdGivenDate(
            LocalDate saleDate, String id, String expectedId) {
        // Given
        when(database.getLastId()).thenReturn(id);
        IdService idService = new IdService(database);

        // When
        String actualId = idService.getNewId(saleDate);

        // Then
        assertThat(actualId, is(expectedId));
    }

    @Test
    void isIdPresent() {
        // Given
        String id = "20200101_0001";
        IdService idService = new IdService(database);

        // When
        idService.isIdPresent(id);

        // Then
        verify(database).containsId(id);
    }

    private static Stream<Arguments> getNewIdArguments() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2000, 1, 1),
                        "20200101_0123",
                        "20000101_0124"),
                Arguments.of(
                        LocalDate.of(2020, 2, 2),
                        "20200101_1235",
                        "20200202_1236"),
                Arguments.of(
                        LocalDate.of(2020, 12, 31),
                        "20200101_0005",
                        "20201231_0006"),
                Arguments.of(
                        LocalDate.of(2123, 11, 11),
                        "20200101_0999",
                        "21231111_1000")
        );
    }
}
