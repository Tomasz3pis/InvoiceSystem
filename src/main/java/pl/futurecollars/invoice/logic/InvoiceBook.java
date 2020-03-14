package pl.futurecollars.invoice.logic;

import pl.futurecollars.invoice.db.DataBase;
import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.model.Invoice;
import java.util.List;

public class InvoiceBook implements DataBase {
    private List<Invoice> invoices;
    private InMemoryDataBase database;

    @Override
    public void saveInvoice(final Invoice invoice) {
    invoices.add(invoice);
    }

    @Override
    public List<Invoice> getInvoices() {
        return database.getInvoices();
    }

    @Override
    public void getInvoiceById(String id) {

    }

    @Override
    public void updateInvoice(Invoice invoice) {

    }

}



// save invoice,  delete, update invoice, searchInvoices