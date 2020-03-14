package pl.futurecollars.invoices.helpers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.helpers.CheckIdFormat.checkIdFormat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CheckIdFormatTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "20200101_0001",
            "20000101_0001",
            "20190101_0001",
            "20201201_0001",
            "20201231_0001",
            "20200101_9999",
    })
    void shouldCheckIdFormat(String id) {
        checkIdFormat(id);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "0",
            "a",
            "12345678_0000",
            "19990101_0001",
            "20201301_0001",
            "20200132_0001",
            "20200130_000A",
            "20200132.0001",
            "20200132-0001",
            "20200132/0001",
            "20200132,0001",
            "20200101_001"
    })
    void shouldThrowExceptionGivenInvalidId(String id) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                checkIdFormat(id));
        assertThat(exception.getMessage(), is("Provided id String: "
                + id + " is not a valid id format"));
    }
}
