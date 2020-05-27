package pl.futurecollars.invoices.database.invoice.multifile;

import org.springframework.context.annotation.Configuration;
import pl.futurecollars.invoices.exceptions.InvoiceNotFoundException;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class MultiFileDbCache {

    private static final Map<Long, File> INVOICE_ID_TO_FILE_MAP = new HashMap<>();

    public void add(final Long id, final File fileName) {
        if (id <= 0) {
            throw new InvalidParameterException("Invalid ID value = " + id + ". Cannot be lower than 1.");
        }
        if (!fileName.exists()) {
            throw new InvalidParameterException("File : " + fileName + " do not exist.");
        }
        INVOICE_ID_TO_FILE_MAP.put(id, fileName);
    }

    public Optional<File> get(final Long id) {
       return Optional.ofNullable(INVOICE_ID_TO_FILE_MAP.get(id));
    }

    public void remove(Long id) {
        File deletedInvoice = INVOICE_ID_TO_FILE_MAP.remove(id);
        if (deletedInvoice == null) {
            throw new InvoiceNotFoundException(id);
        }
    }
}
