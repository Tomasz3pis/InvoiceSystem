package pl.futurecollars.invoices.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.JsonParserHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileHelper {

    private final Logger logger = LoggerFactory.getLogger(FileHelper.class);

    @Autowired
    private JsonParserHelper jsonParserHelper;

    @Value("${infile.db.path}")
    private String inFileDbPath;

    @Value("${infile.counter.path}")
    private String inFileCounterPath;

    public void appendLine(String json) {
        File file = new File(inFileDbPath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(json + "\n");
        } catch (IOException ex) {
            logger.error("Error opening file");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public Invoice findById(Long invoiceId) {
        File file = new File(inFileDbPath);
        String patternString = constructIdPattern(invoiceId);
        Pattern specifiedIdPattern = Pattern.compile(patternString);
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines.filter(line -> specifiedIdPattern.matcher(line).matches())
                    .findFirst()
                    .map(matchingLine -> jsonParserHelper.jsonToInvoice(matchingLine))
                    .orElse(null);
        } catch (IOException ex) {
            logger.error("Error opening file");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public List<Invoice> findAll(LocalDate startDate, LocalDate endDate) {
        File file = new File(inFileDbPath);
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines
                    .map(line -> jsonParserHelper.jsonToInvoice(line))
                    .filter(invoice -> invoice.getIssueDate().isAfter(startDate))
                    .filter(invoice -> invoice.getIssueDate().isBefore(endDate))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("File with input date cannot be opened");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public Invoice deleteById(Long invoiceId) {
        File file = new File(inFileDbPath);
        try (Stream<String> lines = Files.lines(file.toPath())) {
            List<String> out = lines
                    .filter(line -> !line.contains(String.format("\"id\":%d", invoiceId)))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            logger.error("File cannot be opened");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void updateById(Long invoiceId, String json) {
        deleteById(invoiceId);
        appendLine(json);
    }

    public List<Invoice> findAll() {
        File file = new File(inFileDbPath);
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines
                    .map(jsonParserHelper::jsonToInvoice)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("File cannot be opened");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public Long readNextCounter() {
        try (
                FileInputStream fis = new FileInputStream(inFileCounterPath);
                Scanner sc = new Scanner(fis)
        ) {
            Integer counter;
            counter = sc.nextInt();
            counter++;
            Path path = Paths.get(inFileCounterPath);
            String num = counter.toString().trim();
            Files.write(path, num.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return counter.longValue();
        } catch (IOException ex) {
            logger.error("Error opening counter file");
        }
        return null;
    }

    private String constructIdPattern(Long id) {
        return String.format("^(\\{\"id\":%d,){1}.*", id);
    }
}
