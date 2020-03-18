package pl.futurecollars.invoice.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.futurecollars.invoice.model.Invoice;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabase implements DataBase {
    private List<Invoice> invoices = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    private String json;

    @Override
    public void saveInvoice(Invoice invoice) throws IOException {
        mapper.writeValue(new File("invoiceList.json"), invoice);
        json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(invoice);
    }

    @Override
    public List<Invoice> getInvoices() throws IOException {
        Invoice invoiceFromJson = mapper.readValue(json, Invoice.class);
        invoices.add(invoiceFromJson);
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(String id) throws IOException {
        for (Invoice element : invoices) {
            if (invoices.equals(id)) {
                return element;
            }
        }
        throw new IllegalArgumentException("Invoice id: " + id + " not found in Database.");
    }

    @Override
    public void updateInvoice(Invoice invoice, int updatedIndex) {
        if(updatedIndex > invoices.size()) {
            throw new IndexOutOfBoundsException ("List of invoices is between : 0 to  " + invoices.size() + " Please pass correct index.");
        }
    }

    @Override
    public void deleteInvoice(String id) {

    }

}

