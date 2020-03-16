package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase implements DataBase {
    private List<Invoice> invoices = new ArrayList<>();

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public List<Invoice> getInvoices() {
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
