package pl.futurecollars.invoice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.jparams.verifier.tostring.ToStringVerifier;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

        //when

        //then
        ToStringVerifier.forClass(Company.class)
                .verify();
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

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldVerifyIfEqualWorksCorrectly(Company company, Company otherCompany, boolean expected) {
        //given

        //when
        boolean actual = company.equals(otherCompany);

        //then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> dataProvider() {
        Company company1 = new Company(12333211L, "Here", "PC");
        Company company2 = new Company(321313L, "There", "LnX");
        Company company3 = new Company(321313L, "There", "LnX");

        return Stream.of(
                Arguments.of(company1, company2, false),
                Arguments.of(company2, company3, true),
                Arguments.of(company1, null, false)
        );
    }
}
