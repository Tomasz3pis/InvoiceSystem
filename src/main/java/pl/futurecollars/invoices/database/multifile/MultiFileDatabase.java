package pl.futurecollars.invoices.database.multifile;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.database.AbstractDatabase;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;
import pl.futurecollars.invoices.model.Invoice;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Repository
@Primary
public class MultiFileDatabase extends AbstractDatabase {

    private static final Logger LOGGER =
            LogManager.getLogger(MultiFileDatabase.class.getName());
    private static final String FILE_EXTENSION = ".json";
    private static Long currentId;

    @Autowired
    private MultiFileDbCache cache;

    @Autowired
    private ObjectMapper mapper;

    @Value("${multifile.db.path}")
    private String databaseBaseDirectory;

    @Value("${multifile.id.path}")
    private String idFile;

    @PostConstruct
    public void initilizeCache() {
        try {
                for (File file : findAllFiles()) {
                   List<Invoice> invoices = (mapper.readValue(file, mapper.getTypeFactory()
                            .constructCollectionType(List.class, Invoice.class)));
                   for (Invoice invoice : invoices) {
                       cache.add(invoice.getId(), file);
                   }
                }
            } catch (IOException e) {
                LOGGER.error("Cannot read invoices. Files do not exist or are unable to read. ", e);
            }
        }


    @Override
    public long saveInvoice(Invoice invoice) {
        try {
            currentId = readCurrentId();
            invoice.setId(nextIdNumber());
            writeInvoiceToCorrectFile(invoice);
        } catch (IOException e) {
            LOGGER.error("Cannot save invoice. Failed to create file.", e);
        }
        return invoice.getId();
    }

    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        try {
            for (File file : findAllFiles()) {
                invoices.addAll(mapper.readValue(file, mapper.getTypeFactory()
                        .constructCollectionType(List.class, Invoice.class)));
            }
        } catch (IOException e) {
            LOGGER.error("Cannot read invoices. Files do not exist or are unable to read. ", e);
        }
        return invoices;
    }

    @Override
    public Optional<Invoice> getInvoiceById(long id) {
        Optional<File> invoiceFile = cache.get(id);
        try {
            if (invoiceFile.isEmpty()) {
                return Optional.empty();
            }
            return readInvoiceListFromFile(invoiceFile.get())
                    .stream()
                    .filter(invoice -> invoice.getId() == id)
                    .findAny();
        } catch (IOException e) {
            LOGGER.error("Cannot read invoice with id = " + id + ". File do not exist or is unable to read.", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateInvoice(long id, Invoice updatedInvoice) {
        if (cache.get(id).isEmpty()) {
            throw new InvoiceNotFoundException(id);
        }
        deleteInvoice(id);
        updatedInvoice.setId(id);
        try {
            writeInvoiceToCorrectFile(updatedInvoice);
        } catch (IOException e) {
            LOGGER.fatal("Cannot update invoice with id = " + id + ". File do not exist or is unable to read.", e);
        }
    }

    @Override
    public void deleteInvoice(long id) {
        if (cache.get(id).isEmpty()) {
            throw new InvoiceNotFoundException(id);
        }
        File file = cache.get(id).get();
        cache.remove(id);
        try {
            List<Invoice> collect = readInvoiceListFromFile(file)
                    .stream()
                    .filter(invoice -> invoice.getId() != id)
                    .collect(Collectors.toList());
            if (collect.isEmpty()) {
                file.delete();
            } else {
                for (Invoice invoice : collect) {
                    mapper.writeValue(file, invoice);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Cannot delete invoice with id = " + id + ". File do not exist or is unable to read.", e);
        }
    }

    private File invoiceFile(Invoice invoice) throws IOException {
        return createFileIfNeeded(preciseFileLocation(invoice));
    }

    private File createFileIfNeeded(String path) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    private String preciseFileLocation(Invoice invoice) {
        int year = invoice.getIssueDate().getYear();
        Month month = invoice.getIssueDate().getMonth();
        int dayOfMonth = invoice.getIssueDate().getDayOfMonth();

        return databaseBaseDirectory + File.separator
                + year + File.separator
                + month + File.separator
                + dayOfMonth
                + FILE_EXTENSION;
    }

    private List<File> findAllFiles() throws IOException {

        return Files.walk(Paths.get(databaseBaseDirectory))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private Long nextIdNumber() throws IOException {
        createFileIfNeeded(idFile);
        currentId++;
        Files.write(Paths.get(idFile),
                currentId.toString().trim().getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
        return currentId;
    }

    private Long readCurrentId() throws FileNotFoundException {
        if (!new File(idFile).exists()) {
            return 0L;
        }
        FileInputStream fileInputStream = new FileInputStream(idFile);
        Scanner scanner = new Scanner(fileInputStream);
        Integer id = 0;
        if (scanner.hasNextInt()) {
            id = scanner.nextInt();
        }
        return Long.valueOf(id);
    }

    private List<Invoice> readInvoiceListFromFile(File file) throws IOException {
        return mapper.readValue(file, mapper
                .getTypeFactory()
                .constructCollectionType(List.class, Invoice.class));
    }

    private void writeInvoiceToCorrectFile(Invoice invoice) throws IOException {
        mapper.writeValue(invoiceFile(invoice), invoice);
        cache.add(invoice.getId(), new File(preciseFileLocation(invoice)));
    }
}
