package pl.futurecollars.invoice.logic;

import pl.futurecollars.invoice.model.Invoice;

public class InvoiceProvider {
    // Class that generate inovoice for further tests with Entries
    // new invoice with 5 entry
    //another method add invoice with noEntry
    //


    private Invoice invoice = new Invoice();

    InvoiceProvider withCity(String city) {
        this.invoice.setCity(city);
        return this;
    }

    Invoice build() {
        return invoice;
    }

}
