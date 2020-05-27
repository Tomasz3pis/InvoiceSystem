package pl.futurecollars.invoices.database;

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
import org.springframework.test.util.ReflectionTestUtils;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.JsonParserHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FileHelperTest {

    @InjectMocks
    private FileHelper fileHelper;

    @Mock
    private JsonParserHelper jsonParserHelper;

    @SuppressWarnings("VisibilityModifier")
    @TempDir
    File tempDir;

    private File db;
    private File counter;

    @BeforeEach
    public void setUp() throws IOException {
        db = new File(tempDir.getAbsolutePath() + "/db.txt");
        db.createNewFile();
        counter = new File(tempDir.getAbsolutePath() + "/counter.txt");
        ReflectionTestUtils.setField(fileHelper, "inFileDbPath", db.getAbsolutePath());
        ReflectionTestUtils.setField(fileHelper, "inFileCounterPath", counter.getAbsolutePath());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(counter))) {
            bufferedWriter.write("0");
        }
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(db, true))) {
            bufferedWriter.write(json + "\n");
            bufferedWriter.write(json2);
        }
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(db, true))) {
            bufferedWriter.write(json + "\n");
        }
        // when
        fileHelper.deleteById(65L);
        // then
        Invoice existingInvoice = fileHelper.findById(65L);
        assertThat(existingInvoice).isNull();
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
        File resultFile = new File(path);
        try (Stream<String> lines = Files.lines(resultFile.toPath())) {
            return lines
                    .collect(Collectors.toList());
        }
    }
}
