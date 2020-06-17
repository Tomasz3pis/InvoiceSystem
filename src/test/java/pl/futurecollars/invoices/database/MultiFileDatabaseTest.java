package pl.futurecollars.invoices.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceOne;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceTwo;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import pl.futurecollars.invoices.database.invoice.multifile.MultiFileDatabase;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
class MultiFileDatabaseTest {

    @Autowired
    private MultiFileDatabase database;

    @Value("${multifile.db.path}")
    private String invoiceFile;

    @Value("${multifile.id.path}")
    private String idFile;

    @BeforeEach
    void cleanUpFiles() throws IOException {
        if (new File(invoiceFile).exists()) {
            FileUtils.cleanDirectory(new File(invoiceFile));
        }
        new File(idFile).delete();
    }

    @Test
    void shouldSaveInvoices() {
        //given
        Invoice invoiceOne = getInvoiceOne();
        Invoice invoiceTwo = getInvoiceTwo();

        //when
        database.saveInvoice(invoiceOne);
        database.saveInvoice(invoiceTwo);


        //then
        assertThat(database.getInvoices(), hasItems(invoiceOne, invoiceTwo));
        assertThat(database.getInvoiceById(invoiceOne.getId()), is(Optional.of(invoiceOne)));
    }

    @Test
    void shouldReturnEmptyList() {
        //given

        //when

        //then
        assertThat(database.getInvoices(), is(Collections.emptyList()));
        assertThat(database.getInvoices(null, LocalDate.of(2000, 1, 1)), is(Collections.emptyList()));
    }

    @Test
    void shouldUpdateSingleInvoice() {
        //given
        Invoice invoiceOne = getInvoiceOne();
        Invoice invoiceTwo = getInvoiceTwo();
        database.saveInvoice(invoiceOne);

        //when
        assertThat(database.getInvoices(), hasItems(invoiceOne));
        database.updateInvoice(invoiceOne.getId(), invoiceTwo);

        //then
        assertThat(database.getInvoices(), hasItems(invoiceTwo));
    }

    @Test
    void updateInvoiceShouldThrowExceptionGivenNotPresentInvoice() {
        // given
        Invoice invoice = getInvoiceOne();
        long id = 5L;

        // when

        // then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.updateInvoice(id, invoice));
        assertThat(exception.getMessage(), is(
                "Invoice with provided id does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }

    @Test
    void shouldDeleteInvoiceGivenId() {
        // given
        Invoice invoice = getInvoiceOne();
        database.saveInvoice(invoice);
        long id = invoice.getId();
        assertThat(database.getInvoiceById(id), is(Optional.of(invoice)));

        // when
        database.deleteInvoice(id);

        // then
        assertThat(database.getInvoiceById(id), is(Optional.empty()));
    }

    @Test
    void deleteInvoiceShouldThrowExceptionGivenNotExistingId() {
        // given
        long id = 100000L;

        // when

        // then
        Exception exception = assertThrows(InvoiceNotFoundException.class, () -> database.deleteInvoice(id));
        assertThat(exception.getMessage(), is(
                "Invoice with provided id does not exist in database. "
                        + "Invoice id: " + id + " not found."));
    }

    @Test
    void shouldReturnInvoicesInGivenPeriodOfTime() {
        //given
        Invoice invoiceOne = getInvoiceOne();
        Invoice invoiceTwo = getInvoiceTwo();
        database.saveInvoice(invoiceOne);
        database.saveInvoice(invoiceTwo);

        //when
        LocalDate dateOne = LocalDate.of(2018, 1, 1);
        LocalDate dateTwo = LocalDate.of(2020, 1, 1);

        //then
        assertThat(database.getInvoices(null, null), hasItems(invoiceOne, invoiceTwo));
        assertThat(database.getInvoices(null, dateTwo), hasItems(invoiceOne));
        assertThat(database.getInvoices(dateOne, dateTwo), hasItems(invoiceOne));
    }
}
