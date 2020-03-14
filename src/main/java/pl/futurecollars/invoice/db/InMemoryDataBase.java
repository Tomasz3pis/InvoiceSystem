package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.List;

public class InMemoryDataBase implements DataBase {
    private DataBase dataBase;  /// This should be arrayList

    public InMemoryDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void saveInvoice(Invoice invoice) {
        dataBase.saveInvoice(invoice);
    }

    @Override
    public List<Invoice> getInvoices() {
        return dataBase.getInvoices();
    }

}
// Add list instead of Database class - database = arrayList
//