package pl.CodersTrust.invoice.database;

import org.junit.jupiter.api.Test;
import pl.CodersTrust.invoice.model.Company;
import pl.CodersTrust.invoice.model.Invoice;


import java.time.LocalDate;
import java.util.ArrayList;


class InMemoryDatabaseTest {

    @Test
    void saveInvoice() {
        //given
        Company seller = new Company(789123324, "Test st.", "CrownCH");
        Company buyer = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice = new Invoice(seller, buyer, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);

        //then


    }

    @Test
    void updateInvoice() {
    }

    @Test
    void deleteInvoice() {
    }

    @Test
    void getInvoiceById() {
    }

    @Test
    void getInvoices() {
    }
}