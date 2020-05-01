package pl.futurecollars.invoices.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.model.JsonParserHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class InFileDatabase implements Database {

    private final Logger logger = LoggerFactory.getLogger(InFileDatabase.class);

    @Autowired
    private JsonParserHelper jsonParserHelper;
    
    @Autowired
    private FileHelper fileHelper;

    @Override
    public long saveInvoice(Invoice invoice) {
        invoice.setId(fileHelper.readNextCounter());
        String json = jsonParserHelper.objectToJson(invoice);
        fileHelper.appendLine(json);
        return 0;
    }

    @Override
    public List<Invoice> getInvoices() {
        return fileHelper.findAll();
    }

    @Override
    public List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate) {
        return fileHelper.findAll(startDate, endDate) ;
    }

    @Override
    public Optional<Invoice> getInvoiceById(long id) {
        return Optional.ofNullable(fileHelper.findById(id));
    }

    @Override
    public void updateInvoice(long id, Invoice updatedInvoice) {
        String json = jsonParserHelper.objectToJson(updatedInvoice);
        fileHelper.updateById(id, json);
    }

    @Override
    public void deleteInvoice(long id) {
        fileHelper.deleteById(id);
    }
}