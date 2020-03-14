package pl.futurecollars.invoice.db;

import pl.futurecollars.invoice.model.Invoice;
import java.util.List;

public class InFileDatabase implements DataBase {

    @Override
    public void saveInvoice(Invoice invoice) {

    }

    @Override
    public List<Invoice> getInvoices() {
        return null;
    }
}


//Rozszerzamy inMemory - faktura ma być zapisana na pliku zamiast w Liscie jak w klasie InMemory
// Musimy móc ustawić przełączenie się w bazach danych w propertisach. Nie zmieniamy nic w kodzie ustawiamy properity
// SQL detabase
// Wszystkie klasy z pakietu DB powinny mieć metody saveInvoice, getInvoiceById, getInvoices, updateInvoice
