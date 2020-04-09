package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressLatrobe;
import static pl.futurecollars.invoices.providers.TestAddressProvider.addressRoseville;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CompanyTest {

    private PostalAddress postalAddress = addressRoseville();
    private PostalAddress secondPostalAddress = addressLatrobe();

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldConstructCompanyGivenArguments(String taxIdentificationNumber, String name) {
        // Given

        // When
        Company company = new Company(taxIdentificationNumber, name, postalAddress);

        // Then
        assertThat(company.getTaxIdentificationNumber(), is(taxIdentificationNumber));
        assertThat(company.getName(), is(name));
        assertThat(company.getAddress(), is(postalAddress));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldReturnTrueGivenEqualCompanies(String taxIdentificationNumber, String name) {
        // Given

        // When
        Company firstCompany = new Company(taxIdentificationNumber, name, postalAddress);
        Company secondCompany = new Company(taxIdentificationNumber, name, postalAddress);

        // Then
        assertThat(firstCompany.equals(secondCompany), is(true));
        assertThat(firstCompany.equals(firstCompany), is(true));
        assertThat(secondCompany.equals(secondCompany), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseGivenDifferentCompanies(String taxIdentificationNumber, String name) {
        // Given

        // When
        Company firstCompany = new Company("123-456-78-90", "NameOne", postalAddress);

        Company secondCompany = new Company(taxIdentificationNumber, name, postalAddress);

        Company thirdCompany = new Company("123-456-78-90", "NameOne", secondPostalAddress);

        // Then
        assertThat(firstCompany.equals(secondCompany), is(false));
        assertThat(firstCompany.equals(thirdCompany), is(false));
        assertThat(firstCompany.equals("otherClassObject"), is(false));
        assertThat(firstCompany.equals(null), is(false));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldReturnHashCodeGivenCompany(String taxIdentificationNumber, String name) {
        // Given

        // When
        Company company = new Company(taxIdentificationNumber, name, postalAddress);
        Company secondCompany = new Company(taxIdentificationNumber, "someOtherName", postalAddress);

        // Then
        assertNotEquals(0, company.hashCode());
        assertNotEquals(company.hashCode(), secondCompany.hashCode());
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldReturnStringGivenCompany(String taxIdentificationNumber, String name) {
        // Given

        // When
        Company company = new Company(taxIdentificationNumber, name, postalAddress);

        // Then
        assertNotEquals(null, company.toString());
        assertNotEquals("", company.toString());
    }

    private static Stream<Arguments> companyConstructorArguments() {
        return Stream.of(
                Arguments.of("1234567890", "NameOne"),
                Arguments.of("2345678901", "Name Two"),
                Arguments.of("3456789012", "Name 3"),
                Arguments.of("4567890123", "Name of Fourth Company inc.")
        );
    }

    private static Stream<Arguments> notEqualsArguments() {
        return Stream.of(
                Arguments.of("9999999999", "NameOne"),
                Arguments.of("1234567890", "NameOne changed")
        );
    }
}
