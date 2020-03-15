package pl.CodersTrust.invoice.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.Objects;

import static org.hamcrest.Matchers.is;


class CompanyTest {

    @Test
    void shouldReturnTaxIdentificationNumber() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        long actual = company.getTaxIdentificationNumber();

        //then
        Assert.assertThat(actual, is(7899877789L));

    }

    @Test
    void shouldSetNewTaxIdentificationNumber() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        company.setTaxIdentificationNumber(1231234455L);

        //then
        Assert.assertThat(company.getTaxIdentificationNumber(), is(1231234455L));

    }

    @Test
    void shouldReturnAddress() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        String actual = company.getAddress();

        //then
        Assert.assertThat(actual, is("Mount st"));
    }

    @Test
    void shouldSetNewAddress() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        company.setAddress("Hill st");

        //then
        Assert.assertThat(company.getAddress(), is("Hill st"));
    }

    @Test
    void shouldReturnName() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        String actual = company.getName();

        //then
        Assert.assertThat(actual, is("PlaceHolder"));
    }

    @Test
    void shouldSetNewName() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        company.setName("New Place");

        //then
        Assert.assertThat(company.getName(), is("New Place"));
    }

    @Test
    void shouldReturnStringRepresentationOfCompany() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        String actual = company.toString();

        //then
        Assert.assertThat(actual, is("Company{"
                + "taxIdentificationNumber=" + company.getTaxIdentificationNumber()
                + ", address='" + company.getAddress() + '\''
                + ", name='" + company.getName() + '\''
                + '}'));
    }

    @Test
    void shouldReturnHashCode() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        int actual = company.hashCode();

        //then
        Assert.assertThat(actual, is(Objects.hash(company.getTaxIdentificationNumber(), company.getAddress(), company.getName())));

    }

    @Test
    void shouldReturnFalse() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");
        Company company2 = new Company(1231231231L, "Mount st", "Different name");

        //when
        boolean actual = company.equals(company2);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    void shouldReturnTrue() {
        //given
        Company company = new Company(7899877789L, "Mount st", "PlaceHolder");
        Company company2 = new Company(7899877789L, "Mount st", "PlaceHolder");

        //when
        boolean actual = company.equals(company2);

        //then
        Assert.assertTrue(actual);
    }
}