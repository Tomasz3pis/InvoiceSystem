package pl.futurecollars.invoices.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class FileHelper {

    final Logger logger = LoggerFactory.getLogger(FileHelper.class);

    @Autowired
    private InFileDbConfig inFileDbConfig;

    @Autowired
    private JsonParserHelper jsonParserHelper;

    public void appendLine(String json) {
        try {
            json = String.format("%s\n", json);
            File file = new File(inFileDbConfig.getDbFilePath());
            FileWriter writer = new FileWriter(file, true);
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            logger.error("asdas");
        }
    }

    public Invoice findById(Long invoiceId) {
        try {
            FileInputStream fis = new FileInputStream(inFileDbConfig.getDbFilePath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String json = sc.nextLine();
                Invoice invoice = (Invoice) jsonParserHelper.jsonToObject(json, Invoice.class);
                if (invoice.getId() == invoiceId) {
                    sc.close();
                    fis.close();
                    return invoice;
                }
            }
        } catch (IOException | NullPointerException ex) {
            // fix this log
            logger.error("Input id is wrong");
        }
        return null;
    }

    public Invoice deleteById(Long invoiceId) {
        try {
            File file = new File(inFileDbConfig.getDbFilePath());
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(String.format("\"id\":%d", invoiceId)))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException | NullPointerException ex) {
            // fix this log
            logger.error("Provided id is wrong or invoice does  not exist");
        }
        return null;
    }

    public void updateById(Long invoiceId, String json) {
        deleteById(invoiceId);
        appendLine(json);
    }

    public List<Invoice> findAll() {
        List<Invoice> listOfAll = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(inFileDbConfig.getDbFilePath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String json = sc.nextLine();
                listOfAll.add((Invoice) jsonParserHelper.jsonToObject(json, Invoice.class));
            }
        } catch (IOException | NullPointerException ex) {
            logger.error("Error while searching for record.");
        }
        return listOfAll;
    }

    public Long readNextCounter() {
        try {
            FileInputStream fis = new FileInputStream(inFileDbConfig.getCounterFilePath());
            Scanner sc = new Scanner(fis);
            Integer counter = null;
            while (sc.hasNextLine()) {
                counter = sc.nextInt();
                counter++;
                Path path = Paths.get(inFileDbConfig.getCounterFilePath());
                String num = counter.toString().trim();
                Files.write(path, num.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                return counter.longValue();
            }
        } catch (IOException | NullPointerException ex) {
            logger.error("");
        }
        return null;
    }
}
