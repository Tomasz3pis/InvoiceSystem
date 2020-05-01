package pl.futurecollars.invoices.database;

import org.apache.el.util.ReflectionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.JsonParserHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FileHelperTest {

    @InjectMocks
    private FileHelper fileHelper;

    @Mock
    private JsonParserHelper jsonParserHelper;

    @TempDir
    public File tempDir;
    private File db;
    private File counter;

    @BeforeEach
    public void setUp() throws IOException {
        db = new File(tempDir.getAbsolutePath() + "/db.txt");
        counter = new File(tempDir.getAbsolutePath() + "/counter.txt");
        ReflectionTestUtils.setField(fileHelper, "inFileDbPath", db.getAbsolutePath());
        ReflectionTestUtils.setField(fileHelper, "inFileCounterPath", counter.getAbsolutePath());
        FileWriter writer = new FileWriter(counter, true);
        writer.write("0");
        writer.close();
    }

    @AfterEach
    public void tearDown() {
        db.delete();
        counter.delete();
    }

    @Test
    void shouldAppendLine() throws IOException {
        //given
        String json = "testString";
        //when
        fileHelper.appendLine(json);
        //then
        List<String> dbLines = getFileLines(db.getAbsolutePath());
        assertThat(dbLines).hasSize(1);
        assertThat(dbLines.get(0)).isEqualTo(json);
    }

    @Test
    void findById() throws IOException {
        // given
        String json = "{\"id\":65, \"\"}";
        Invoice invoice = new Invoice();
        invoice.setId(65L);
        String json2 = "{\"id\":102, \"\"}";
        Invoice invoice2 = new Invoice();
        invoice2.setId(102L);
        FileWriter writer = new FileWriter(db, true);
        writer.write(json+"\n");
        writer.write(json2);
        writer.close();

        when(jsonParserHelper.jsonToInvoice(json)).thenReturn(invoice);
        when(jsonParserHelper.jsonToInvoice(json2)).thenReturn(invoice2);
        //when
        Invoice result = fileHelper.findById(102L);
        //then
        assertThat(result.getId()).isEqualTo(102L);
    }

    @Test
    void deleteById() throws IOException {
        // given
        String json = "{\"id\":65, \"\"}";
        Invoice invoice = new Invoice();
        invoice.setId(65L);
        FileWriter writer = new FileWriter(db, true);
        writer.write(json+"\n");
        writer.close();
        // when
        fileHelper.deleteById(65L);
        // then
        InvoiceNotFoundException exception = catchThrowableOfType(()-> fileHelper.findById(65L),InvoiceNotFoundException.class);
        assertThat(exception).isNotNull();

    }

    @Test
    void updateById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void readNextCounter() {
    }

    private List<String> getFileLines(String path) throws IOException {
        List<String> resultList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {
            resultList.add(sc.nextLine());
        }
        sc.close();
        fis.close();
        return resultList;
    }

    private class Strictness {
    }
}
