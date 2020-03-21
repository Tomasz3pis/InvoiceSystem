package pl.CodersTrust.invoice.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CompanyTest {

    @Test
    void shouldReturnSetTaxIdentificationNumber() {
        //given
        Company company = new Company(0L, "", "");

        //when
        company.setTaxIdentificationNumber(1231234455L);

        //then
        assertEquals(company.getTaxIdentificationNumber(), 1231234455L);
    }

    @Test
    void shouldReturnSetAddress() {
        //given
        Company company = new Company(0L, "", "");

        //when
        company.setAddress("Hill st");

        //then
        assertEquals(company.getAddress(), "Hill st");
    }

    @Test
    void shouldReturnSetName() {
        //given
        Company company = new Company(0L, "", "");

        //when
        company.setName("New Place");

        //then

        assertEquals(company.getName(), "New Place");
    }

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        Company company = new Company(0L, "", "");

        //when
        Company company2 = new Company(8L, "", "");

        //then
        assertNotEquals(company.hashCode(), company2.hashCode());

    }

    @Test
    void shouldCheckIfToStringIsNotEmptyOrNull() {
        //given
        Company company = new Company(0L, "", "");

        //when
        String actual = company.toString();

        //then
        assertNotEquals(null, actual);
        assertNotEquals("", actual);

    }

    @Test
    void shouldReturnHashCode() {
        //given
        Company company = new Company(0L, "", "");

        //when
        int actual = company.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(company));
    }
}
