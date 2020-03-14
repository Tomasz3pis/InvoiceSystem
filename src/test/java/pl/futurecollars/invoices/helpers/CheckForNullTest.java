package pl.futurecollars.invoices.helpers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;

import org.junit.jupiter.api.Test;

class CheckForNullTest {

    @Test
    void shouldThrowExceptionGivenNullObject() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                checkForNull(null, "someName"));
        assertThat(exception.getMessage(), is("Provided "
                + "someName"
                + " Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullObjectName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                checkForNull("someObject", null));
        assertThat(exception.getMessage(), is(
                "Provided objectName String cannot be null"));
    }
}
