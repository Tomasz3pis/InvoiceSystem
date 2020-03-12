package pl.CodersTrust.invoice.database;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.CodersTrust.invoice.model.Company;
import pl.CodersTrust.invoice.model.Invoice;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
class InMemoryDatabaseTest {

    @Test
    void shouldSaveInvoiceInDatabase() {
        //given
        Company seller = new Company(789123324, "Test st.", "CrownCH");
        Company buyer = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice = new Invoice(seller, buyer, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);

        //then
        Assert.assertTrue(imd.getInvoices().contains(invoice));

    }

    @Test
    void updateInvoice() {
    }

    @Test
    void shouldDeleteGivenInvoiceFromDatabase() {
        //given
        Company company1 = new Company(789123324, "Test st.", "CrownCH");
        Company company2 = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice = new Invoice(company1, company2, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();
        imd.saveInvoice(invoice);

        //when
        boolean actual = imd.deleteInvoice(invoice.getId());

        //then
        Assert.assertTrue(actual);

    }

    @Test
    void shouldReturnInvoice() {
        //given
        Company company1 = new Company(789123324, "Test st.", "CrownCH");
        Company company2 = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice = new Invoice(company1, company2, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice);
        Invoice actual = imd.getInvoiceById(1);

        //then
        Assert.assertEquals(actual, invoice);

    }

    @Test
    void shouldReturnListOfInvoices() {
        //given
        Company company1 = new Company(789123324, "Test st.", "CrownCH");
        Company company2 = new Company(749123324, "TestDouble st.", "Virsus");
        Invoice invoice1 = new Invoice(company1, company2, LocalDate.now(), new ArrayList<>());
        Invoice invoice2 = new Invoice(company1, company2, LocalDate.now(), new ArrayList<>());
        InMemoryDatabase imd = new InMemoryDatabase();

        //when
        imd.saveInvoice(invoice1);
        imd.saveInvoice(invoice2);
        List<Invoice> actual = imd.getInvoices();
        ArrayList<Invoice> expected = new ArrayList<>();
        expected.add(invoice1);
        expected.add(invoice2);

        //then
        Assert.assertThat(actual, is(expected));
    }
}
