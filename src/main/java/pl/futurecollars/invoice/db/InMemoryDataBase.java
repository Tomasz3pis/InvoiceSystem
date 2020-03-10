package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;

import java.util.List;

public class InMemoryDataBase implements DataBase {
    private List<Invoice> invoices;

    @Override
    public void saveInvoice(final Invoice invoice) {
        invoices.add(invoice);

    }

    @Override
    public List<Invoice> getInvoices() {
        return invoices;
    }

}
