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
    private Invoice invoiceFromJson;
    private String json;

    @Override
    public void saveInvoice(Invoice invoice) throws IOException {
        mapper.writeValue(new File("invoiceList.json"), invoice);
        json = mapper.writeValueAsString(invoice);
    }

    @Override
    public List<Invoice> getInvoices() throws IOException {
        Invoice invoiceFromJson = mapper.readValue(json, Invoice.class);
        invoices.add(invoiceFromJson);
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(String id) {
        return null;
    }

    @Override
    public void updateInvoice(Invoice invoice, int updatedIndex) {

    }

    @Override
    public void deleteInvoice(String id) {

    }

}


// Rozszerzamy inFile - faktura ma być zapisana na pliku zamiast w Liscie jak w klasie InMemory
// Musimy móc ustawić przełączenie się w bazach danych w propertisach. Nie zmieniamy nic w kodzie ustawiamy properity
// SQL detabase

