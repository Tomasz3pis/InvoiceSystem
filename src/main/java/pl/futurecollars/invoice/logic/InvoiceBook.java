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
    public Invoice getInvoiceById(String id) {
        for (Invoice invoice : invoices) {
            if (invoice.getId().equals(id)) {
                return invoice;
            }
        }
        throw new IllegalArgumentException("Invoice id: " + id + " not found in Database.");
    }

    @Override
    public void updateInvoice(Invoice invoice, int updatedIndex) {
        if(updatedIndex > invoices.size()) {
            throw new IndexOutOfBoundsException ("List of invoices is between : 0 to  " + invoices.size() + " Please pass correct index.");
        }
        invoices.set(updatedIndex,invoice);
    }

    @Override
    public void deleteInvoice(String id) {
        if(!invoices.contains(id)){
            throw new IllegalArgumentException("Invoice id: " + id + " not found in Database.");
        }
        invoices.remove(getInvoiceById(id));
    }

}
