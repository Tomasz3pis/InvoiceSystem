package pl.CodersTrust.invoice.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;


class InvoiceEntryTest {

    @Test
    void shouldReturnDescription() {
        //given
        InvoiceEntry entry = new InvoiceEntry("Test describcion", new BigDecimal(1500L), Vat.VAT_23);

        //when
        String actual = entry.getDescription();

        //then
        Assert.assertThat(actual, is("Test describcion"));

    }

    @Test
    void shouldSetNewDescription() {
        //given
        InvoiceEntry entry = new InvoiceEntry("Test description", new BigDecimal(1500L), Vat.VAT_23);

        //when
        entry.setDescription("New test descri");

        //then
        Assert.assertThat(entry.getDescription(), is("New test descri"));
    }

    @Test
    void shouldReturnValue() {
        //given
        InvoiceEntry entry = new InvoiceEntry("Test describcion", new BigDecimal(1500L), Vat.VAT_23);

        //when
        BigDecimal actual = entry.getValue();

        //then
        Assert.assertThat(actual, is(new BigDecimal(1500L)));

    }

    @Test
    void shouldSetValueTo100() {
        //given
        InvoiceEntry entry = new InvoiceEntry("Test describcion", new BigDecimal(1500L), Vat.VAT_23);

        //when
        entry.setValue(new BigDecimal(100L));

        //then
        Assert.assertThat(entry.getValue(), is(new BigDecimal(100)));

    }

    @Test
    void shouldReturnVatRate() {
        //given
        InvoiceEntry entry = new InvoiceEntry("Test describcion", new BigDecimal(1500L), Vat.VAT_23);

        //when
        Vat actual = entry.getVatRate();

        //then
        Assert.assertThat(actual, is(Vat.VAT_23));

    }

    @Test
    void shouldSetVatRateToVat8() {
        //given
        InvoiceEntry entry = new InvoiceEntry("Test describcion", new BigDecimal(1500L), Vat.VAT_23);

        //when
        entry.setVatRate(Vat.VAT_8);

        //then
        Assert.assertThat(entry.getVatRate(), is(Vat.VAT_8));

    }
}