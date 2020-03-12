package pl.CodersTrust.invoice.service;


import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.database.InMemoryDatabase;
import pl.CodersTrust.invoice.model.Invoice;


public class InvoiceBook {

    private Database database = new InMemoryDatabase();


    private void saveInvoiceInDatabase(Invoice invoice) {
        database.saveInvoice(invoice);
    }

    private Invoice searchInvoiceById(int id) {
        return database.getInvoiceById(id);
    }

    private void filter() {
        database.getInvoices();
        //stream().filter()??
    }
}
