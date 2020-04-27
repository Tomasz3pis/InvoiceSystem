package pl.futurecollars.invoices.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.JsonParserHelper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InFileDatabaseTest {

    @InjectMocks
    private InFileDatabase inFileDatabase;
    @Mock
    private FileHelper fileHelper;
    @Mock
    private JsonParserHelper jsonParserHelper;

    @Test
    void saveInvoice() {
        // given
        Invoice invoice = mock(Invoice.class);
        String json = "testJson";
        when(fileHelper.readNextCounter()).thenReturn(500L);
        when(jsonParserHelper.objectToJson(invoice)).thenReturn(json);
        // when
        inFileDatabase.saveInvoice(invoice);
        // then
        verify(invoice).setId(500L);
        verify(fileHelper).appendLine(json);
    }

    @Test
    void getInvoices() {
    }

    @Test
    void testGetInvoices() {
    }

    @Test
    void getInvoiceById() {
    }

    @Test
    void updateInvoice() {
    }

    @Test
    void deleteInvoice() {
    }
}
