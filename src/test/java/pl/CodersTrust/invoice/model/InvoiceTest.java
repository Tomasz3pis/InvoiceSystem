package pl.CodersTrust.invoice.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;


class InvoiceTest {

    Company company = mock(Company.class);
    InvoiceEntry entry = mock(InvoiceEntry.class);


    @Test
    void shouldReturnSeller() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        Company actual = invoice.getSeller();

        //then
        Assert.assertEquals(company, actual);
    }

    @Test
    void shouldSetSellerToAnother() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));
        Company company2 = mock(Company.class);

        //when
        invoice.setSeller(company2);

        //then
        Assert.assertThat(invoice.getSeller(), is(company2));
    }

    @Test
    void shouldReturnBuyer() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        Company actual = invoice.getBuyer();

        //then
        Assert.assertThat(actual, is(company));

    }

    @Test
    void shouldSetBuyerToAnother() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));
        Company company2 = mock(Company.class);

        //when
        invoice.setBuyer(company2);

        //then
        Assert.assertThat(invoice.getBuyer(), is(company2));
    }

    @Test
    void shouldReturnListOfEntries() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        List<InvoiceEntry> actual = invoice.getEntries();

        //then
        Assert.assertThat(actual, is(List.of(entry)));
    }

    @Test
    void shouldSetEntriesToAnother() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));
        InvoiceEntry entry2 = mock(InvoiceEntry.class);
        //when
        invoice.setEntries(List.of(entry2));

        //then
        Assert.assertThat(invoice.getEntries(), is(List.of(entry2)));
    }

    @Test
    void shouldReturnDate() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        LocalDate actual = invoice.getData();

        //then
        Assert.assertThat(actual, is(LocalDate.of(2020, 3, 14)));

    }

    @Test
    void shouldSetDateTo2015y12m14d() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        invoice.setData(LocalDate.of(2015, 12, 14));

        //then
        Assert.assertThat(invoice.getData(), is(LocalDate.of(2015, 12, 14)));
    }

    @Test
    void shouldReturnStringRepresentationOfInvoice() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        String actual = invoice.toString();

        //then
        Assert.assertThat(actual, is("Invoice{"
                + "id=" + invoice.getId()
                + ", seller=" + invoice.getSeller()
                + ", buyer=" + invoice.getBuyer()
                + ", entries=" + invoice.getEntries()
                + ", data=" + invoice.getData()
                + '}'));
    }

    @Test
    void shouldReturnHashCode() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        int actual = invoice.hashCode();

        //then
        Assert.assertThat(actual, is( Objects.hash(invoice.getId(), invoice.getSeller(), invoice.getBuyer(), invoice.getEntries(), invoice.getData())));

    }

    @Test
    void shouldReturnFalse() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));
        Invoice invoice2 = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        boolean actual = invoice.equals(invoice2);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    void shouldReturnTrue() {
        //given
        Invoice invoice = new Invoice(company, company, LocalDate.of(2020, 3, 14), List.of(entry));

        //when
        boolean actual = invoice.equals(invoice);

        //then
        Assert.assertTrue(actual);
    }
}

