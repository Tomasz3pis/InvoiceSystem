package pl.CodersTrust.invoice.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.CodersTrust.invoice.model.Company;
import pl.CodersTrust.invoice.model.Invoice;


import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(JUnit4.class)
class InvoiceBookTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Company seller = new Company(789123324, "Test st.", "CrownCH");
        Company buyer = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice = new Invoice(seller, buyer, LocalDate.now(), new ArrayList<>());
        InvoiceBook ib = new InvoiceBook();

        //when
        ib.saveInvoiceInDatabase(invoice);

        //then


    }

    @Test
    void shouldReturnInvoice() {
        //given
        Company company1 = new Company(789123324, "Test st.", "CrownCH");
        Company company2 = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice = new Invoice(company1, company2, LocalDate.now(), new ArrayList<>());
        InvoiceBook ib = new InvoiceBook();

        //when
        ib.saveInvoiceInDatabase(invoice);
        Invoice actual = ib.searchInvoiceById(1);

        //then
        Assert.assertEquals(actual, invoice);

    }

}
