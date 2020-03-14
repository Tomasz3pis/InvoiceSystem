package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase implements DataBase {
    private List<Invoice> invoices = new ArrayList<>();

    @Override
    public void saveInvoice(Invoice invoice) {

    }

    @Override
    public List<Invoice> getInvoices() {
        return null;
    }

    @Override
    public void getInvoiceById(String id) {

    }

    @Override
    public void updateInvoice(Invoice invoice) {

    }
}
