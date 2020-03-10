package pl.futurecollars.invoice.logic;

import pl.futurecollars.invoice.db.InMemoryDataBase;
import pl.futurecollars.invoice.model.Invoice;

import java.util.List;

public class InvoiceBook {

    private InMemoryDataBase database = new InMemoryDataBase();

    @Override
    public void saveInvoice(final Invoice invoice) {
        database.saveInvoice(invoice);

    }

    @Override
    public List<Invoice> getInvoices() {
        return database.getInvoices();
    }

}
