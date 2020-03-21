package pl.CodersTrust.invoice.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InvoiceEntryTest {

    @Test
    void shouldReturnSetDescription() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        entry.setDescription("New test descri");

        //then
        assertEquals(entry.getDescription(), "New test descri");
    }

    @Test
    void shouldReturnSetValue() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        entry.setValue(new BigDecimal(100L));

        //then
        assertEquals(entry.getValue(), new BigDecimal(100));
    }


    @Test
    void shouldReturnSetVatRate() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        entry.setVatRate(Vat.VAT_8);

        //then
        assertEquals(entry.getVatRate(), Vat.VAT_8);
    }

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        InvoiceEntry entry2 = new InvoiceEntry("", new BigDecimal(8), Vat.VAT_0);

        //then
        assertNotEquals(entry.hashCode(), entry2.hashCode());

    }

    @Test
    void shouldCheckIfToStringIsNotEmptyOrNull() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        String actual = entry.toString();

        //then
        assertNotEquals(null, actual);
        assertNotEquals("", actual);

    }

    @Test
    void shouldReturnHashCode() {
        //given
        InvoiceEntry entry = new InvoiceEntry("", new BigDecimal(0), Vat.VAT_0);

        //when
        int actual = entry.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(entry));
    }
}
