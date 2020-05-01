package pl.futurecollars.invoices.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.JsonParserHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        try (FileWriter writer = new FileWriter(file, true)) {
            json = String.format("%s\n", json);
            writer.write(json);
        } catch (IOException ex) {
            logger.error("Error writing to file");
        }
    }

    public Invoice findById(Long invoiceId) {
        File file = new File(inFileDbPath);
        String patternString = constructIdPattern(invoiceId);
        Pattern pattern = Pattern.compile(patternString);
        Optional<String> json = null;
        try (Stream<String> lines = Files.lines(file.toPath())) {
            json = lines
                    .filter(line -> pattern.matcher(line).matches())
                    .findFirst();
        } catch (IOException e) {
            logger.error("File could not be opened");
        }
        if (json.isPresent()) {
            return jsonParserHelper.jsonToInvoice(json.get());
        } else {
            throw new InvoiceNotFoundException(invoiceId);
        }
    }

    public List<Invoice> findAll(LocalDate startDate, LocalDate endDate) {
        File file = new File(inFileDbPath);
        List<Invoice> out = new ArrayList<>();
        try (Stream<String> lines = Files.lines(file.toPath())) {
            out = lines
                    .map(line -> jsonParserHelper.jsonToInvoice(line))
                    .filter(invoice -> invoice.getIssueDate().isAfter(startDate) &&
                            invoice.getIssueDate().isBefore(endDate))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("File with input date cannot be opened");
        }
        return out;
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
        }
        return null;
    }

    public void updateById(Long invoiceId, String json) {
        deleteById(invoiceId);
        appendLine(json);
    }

    public List<Invoice> findAll() {
        List<Invoice> listOfAll = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(inFileDbPath);
                Scanner sc = new Scanner(fis)
        ) {
            while (sc.hasNextLine()) {
                String json = sc.nextLine();
                listOfAll.add(jsonParserHelper.jsonToInvoice(json));
            }
        } catch (IOException ex) {
            logger.error("Error while searching for record.");
        }
        return listOfAll;
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
