package pl.futurecollars.invoices.database;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import pl.futurecollars.invoices.database.multifile.MultiFileDbCache;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Optional;

@SpringBootTest
class MultiFileDbCacheTest {

    @Value("${testfile.path}")
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        if (!testFile.exists()) {
            testFile.createNewFile();
        }
    }

    @Test
    void shouldReturnFieldNameOfGivenId() {
        //before
        MultiFileDbCache cache = new MultiFileDbCache();

        //when
        cache.add(1L, testFile);

        //then
        assertThat(cache.get(1L), is(Optional.of(testFile)));
    }

    @Test
    void shouldReturnOptionalEmptyWhenCacheDoesNotContainId() {
        //before
        MultiFileDbCache cache = new MultiFileDbCache();

        //when
        long id = 10000L;

        //then
        assertThat(cache.get(id), is(Optional.empty()));
    }

    @Test
    void shouldRemoveExistingElement() {
        //before
        MultiFileDbCache cache = new MultiFileDbCache();
        long id = 1L;

        //when
        cache.add(id, testFile);
        assertThat(cache.get(id), is(Optional.of(testFile)));
        cache.remove(id);

        //then
        assertThat(cache.get(id), is(Optional.empty()));
    }

    @Test
    void shouldThrowInvalidParameterException() {
        //before
        MultiFileDbCache cache = new MultiFileDbCache();
        long id = 0;

        //when

        //then
        Exception exception = assertThrows(InvalidParameterException.class, () -> cache.add(id, testFile));
        assertThat(exception.getMessage(), is(
                "Invalid ID value = " + id + ". Cannot be lower than 1."));
    }

    @Test
    void shouldThrowInvoiceNotFoundException() {
        //before
        MultiFileDbCache cache = new MultiFileDbCache();
        long id = 0;
        //when

        //then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> cache.remove(id));
        assertThat(exception.getMessage(), is(
                "Invoice with provided id does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }
}
