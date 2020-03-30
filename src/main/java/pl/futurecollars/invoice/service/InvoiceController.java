package pl.futurecollars.invoice.service;

import pl.futurecollars.invoice.db.Database;
import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.model.Invoice;
import java.util.HashMap;

public class InvoiceController implements Database {

    private InMemoryDataBase inMemoryDataBase;

    public InvoiceController(InMemoryDataBase inMemoryDataBase) {
        this.inMemoryDataBase = inMemoryDataBase;
    }

    @Override
    public int saveInvoice(Invoice invoice) {
        return inMemoryDataBase.saveInvoice(invoice);
    }

    @Override
    public HashMap<Integer, Invoice> getInvoices() {
        return inMemoryDataBase.getInvoices();
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        return inMemoryDataBase.getInvoiceById(id);
    }

    @Override
    public void updateInvoice(Invoice invoice, Integer updatedIndex) {
        inMemoryDataBase.updateInvoice(invoice, updatedIndex);
    }

    @Override
    public void deleteInvoice(Integer id) {
        inMemoryDataBase.deleteInvoice(id);
    }
}
