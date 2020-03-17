package pl.CodersTrust.invoice.model;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Objects;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;


class CompanyTest {


    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnSetTaxIdentificationNumber(Company company) {

        company.setTaxIdentificationNumber(1231234455L);

        Assert.assertThat(company.getTaxIdentificationNumber(), is(1231234455L));

    }


    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnSetAddress(Company company) {

        company.setAddress("Hill st");

        Assert.assertThat(company.getAddress(), is("Hill st"));
    }

    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnSetName(Company company) {
        company.setName("New Place");

        Assert.assertThat(company.getName(), is("New Place"));
    }

    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnStringRepresentationOfCompany(Company company) {

        Assert.assertThat(company.toString(), is("Company{"
                + "taxIdentificationNumber=" + company.getTaxIdentificationNumber()
                + ", address='" + company.getAddress() + '\''
                + ", name='" + company.getName() + '\''
                + '}'));
    }

    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnHashCode(Company company) {

        Assert.assertThat(company.hashCode(), is(Objects.hash(company.getTaxIdentificationNumber(), company.getAddress(), company.getName())));
    }

    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnFalse(Company company, Company company2) {

        company2.setName("test");

        Assert.assertFalse(company.equals(company2));
    }

    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnTrue(Company company, Company company2) {

        Assert.assertTrue(company.equals(company2));
    }

    @ParameterizedTest
    @MethodSource("companyProvider")
    void shouldReturnFalseWhenArgIsNull(Company company) {

        Assert.assertFalse(company.equals(null));

    }

    private static Stream<Arguments> companyProvider() {
        Company company = new Company(0L, "", "");
        Company company2 = new Company(0L, "", "");

        return Stream.of(Arguments.of(company, company2));
    }
}