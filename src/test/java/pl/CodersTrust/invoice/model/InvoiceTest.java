package pl.CodersTrust.invoice.model;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;


class InvoiceTest {

    Company company = mock(Company.class);
    InvoiceEntry entry = mock(InvoiceEntry.class);


    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnSetSeller(Invoice invoice) {

        invoice.setSeller(company);

        Assert.assertEquals(invoice.getSeller(), company);
    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnSetBuyer(Invoice invoice) {
        //given
        Company company = mock(Company.class);

        //when
        invoice.setBuyer(company);

        //then
        Assert.assertThat(invoice.getBuyer(), is(company));
    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnSetEntries(Invoice invoice) {
        //given
        InvoiceEntry entry = mock(InvoiceEntry.class);

        //when
        invoice.setEntries(List.of(entry));

        //then
        Assert.assertThat(invoice.getEntries(), is(List.of(entry)));
    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnSetDate(Invoice invoice) {

        invoice.setData(LocalDate.of(2015, 12, 14));

        Assert.assertThat(invoice.getData(), is(LocalDate.of(2015, 12, 14)));
    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnStringRepresentationOfInvoice(Invoice invoice) {

        Assert.assertThat(invoice.toString(), is("Invoice{"
                + "id=" + invoice.getId()
                + ", seller=" + invoice.getSeller()
                + ", buyer=" + invoice.getBuyer()
                + ", entries=" + invoice.getEntries()
                + ", data=" + invoice.getData()
                + '}'));
    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnHashCode(Invoice invoice) {

        Assert.assertThat(invoice.hashCode(), is(Objects.hash(invoice.getId(), invoice.getSeller(), invoice.getBuyer(), invoice.getEntries(), invoice.getData())));

    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnFalse(Invoice invoice, Invoice invoice2) {
        //given
        invoice2.setData(LocalDate.of(2015, 12, 12));
        //when
        boolean actual = invoice.equals(invoice2);
        //then
        Assert.assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnTrue(Invoice invoice, Invoice invoice2) {

        Assert.assertTrue(invoice.equals(invoice2));

    }

    @ParameterizedTest
    @MethodSource("invoiceProvider")
    void shouldReturnFalseWhenArgIsNull(Invoice invoice) {

        Assert.assertFalse(invoice.equals(null));

    }

    private static Stream<Arguments> invoiceProvider() {
        Invoice invoice = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        Invoice invoice1 = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
        return Stream.of(Arguments.of(invoice, invoice1));
    }
}

