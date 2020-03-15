package pl.CodersTrust.invoice;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.CodersTrust.invoice.database.Database;
import pl.CodersTrust.invoice.database.InMemoryDatabase;
import pl.CodersTrust.invoice.model.Company;
import pl.CodersTrust.invoice.model.Invoice;
import pl.CodersTrust.invoice.model.InvoiceEntry;
import pl.CodersTrust.invoice.model.Vat;
import pl.CodersTrust.invoice.service.InvoiceBook;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


class InvoiceApplicationTests {

    private static Database database = new InMemoryDatabase();
    private static InvoiceBook invoiceBook = new InvoiceBook(database);

    @BeforeEach
     void init() {

    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void save(Invoice invoice1, Invoice invoice2) {


        invoiceBook.saveInvoiceInDatabase(invoice1);
        invoiceBook.saveInvoiceInDatabase(invoice2);

        Assert.assertThat(invoiceBook.searchInvoiceById(invoice1.getId()), is(invoice1));
        Assert.assertThat(invoiceBook.searchInvoiceById(invoice2.getId()), is(invoice2));
        Assert.assertThat(database.getInvoices().size(), is(2));

    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void modify(Invoice invoice3, Invoice invoice4) {

        invoiceBook.saveInvoiceInDatabase(invoice3);
        invoice4.setData(LocalDate.of(2018, 6, 8));
        invoice4.setBuyer(new Company(8900988889L, "Monkey st", "Snek"));
        invoice4.setEntries(invoice3.getEntries());
        invoice4.setSeller(new Company(1233211223L, "Unknow", "Hash Corp"));
        database.updateInvoice(invoice3, invoice4);

        Assert.assertThat(database.getInvoiceById(invoice3.getId()), is(invoice4));
        Assert.assertThat(invoice4.getData(), is(LocalDate.of(2018, 6, 8)));
        Assert.assertThat(database.getInvoices().size(), is(1));

    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void remove(Invoice invoice5, Invoice invoice6) {

        invoiceBook.saveInvoiceInDatabase(invoice5);
        invoiceBook.saveInvoiceInDatabase(invoice6);
        database.deleteInvoice(invoice5);

        Assert.assertThat(database.getInvoiceById(invoice5.getId()), nullValue());
        Assert.assertThat(database.getInvoices().size(), is(1));

    }

    private static Stream<Arguments> dataProvider() {
        Company company1 = new Company(7896541234L, "St steerra", "GBC");
        Company company2 = new Company(9080081223L, "Bonsa st", "Unomi");
        InvoiceEntry entry1 = new InvoiceEntry("do this", new BigDecimal(1500), Vat.VAT_23);
        InvoiceEntry entry2 = new InvoiceEntry("do that", new BigDecimal(600), Vat.VAT_8);
        Invoice invoice1 = new Invoice(company1, company2, LocalDate.now(), List.of(entry1, entry2));
        Invoice invoice2 = new Invoice(company2, company1, LocalDate.now(), List.of(entry1));

        return Stream.of(Arguments.of(invoice1, invoice2));

    }
}
