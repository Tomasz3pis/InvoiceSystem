package pl.futurecollars.invoices.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.futurecollars.invoices.model.Invoice.HASH_OFFSET;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class CompanyTest {

    @Mock
    private PostalAddress postalAddress;

    @Mock
    private PostalAddress secondPostalAddress;

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldConstructCompanyGivenArguments(
            String taxIdentificationNumber,
            String name,
            String expectedTaxId) {

        Company company = new Company(
                taxIdentificationNumber,
                name,
                postalAddress);

        assertThat(company.getTaxIdentificationNumber(),
                is(expectedTaxId));
        assertThat(company.getName(), is(name));
        assertThat(company.getAddress(), is(postalAddress));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldSetCompanyFields(
            String taxIdentificationNumber,
            String name,
            String expectedTaxId) {

        Company company = new Company(
                "000 000 00 00",
                "",
                postalAddress);

        company.setTaxIdentificationNumber(taxIdentificationNumber);
        company.setName(name);
        company.setAddress(secondPostalAddress);

        assertThat(company.getTaxIdentificationNumber(),
                is(expectedTaxId));
        assertThat(company.getName(), is(name));
        assertThat(company.getAddress(), is(secondPostalAddress));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorNullArguments")
    void shouldThrowExceptionGivenNull(
            String taxIdentificationNumber,
            String name,
            String nullObjectName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Company(
                        taxIdentificationNumber,
                        name,
                        postalAddress
                )
        );

        assertThat(exception.getMessage(), is("Provided "
                + nullObjectName
                + " Object cannot be null"));
    }

    @Test
    void shouldThrowExceptionGivenNullAddress() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Company(
                        "1234567890",
                        "SomeName",
                        null
                )
        );

        assertThat(exception.getMessage(), is(
                "Provided address Object cannot be null"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "0",
            "123456789",
            "12345678901",
            "123-456-78-901",
            "123-456-78-9",
            "123--456-78-90",
            "123456789O",
            "  1234567890",
            ""
    })
    void shouldThrowExceptionGivenInvalidTaxIdentificationNumber(
            String taxIdentificationNumber) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Company(
                        taxIdentificationNumber,
                        "SomeName",
                        postalAddress
                )
        );
        assertThat(exception.getMessage(), containsString(
                "does not match the ten-digit pattern"));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldReturnTrueGivenEqualCompanies(
            String taxIdentificationNumber,
            String name) {

        Company firstCompany = new Company(
                taxIdentificationNumber,
                name,
                postalAddress);

        Company secondCompany = new Company(
                taxIdentificationNumber,
                name,
                postalAddress);

        assertThat(firstCompany.equals(secondCompany), is(true));
        assertThat(firstCompany.equals(firstCompany), is(true));
        assertThat(secondCompany.equals(secondCompany), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseGivenDifferentCompanies(
            String taxIdentificationNumber,
            String name) {

        Company firstCompany = new Company(
                "123-456-78-90",
                "NameOne",
                postalAddress);

        Company secondCompany = new Company(
                taxIdentificationNumber,
                name,
                postalAddress);

        Company thirdCompany = new Company(
                "123-456-78-90",
                "NameOne",
                secondPostalAddress);

        assertThat(firstCompany.equals(secondCompany), is(false));
        assertThat(firstCompany.equals(thirdCompany), is(false));
        assertThat(firstCompany.equals("otherClassObject"), is(false));
        assertThat(firstCompany.equals(null), is(false));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldReturnHashCodeGivenCompany(
            String taxIdentificationNumber,
            String name,
            String normalizedNumber) {

        Company company = new Company(
                taxIdentificationNumber,
                name,
                postalAddress);

        int expectedHashCode = ((((
                normalizedNumber.hashCode() * HASH_OFFSET)
                + name.hashCode()) * HASH_OFFSET)
                + postalAddress.hashCode());
        assertThat(company.hashCode(), is(expectedHashCode));
    }

    @ParameterizedTest
    @MethodSource("companyConstructorArguments")
    void shouldReturnStringGivenCompany(
            String taxIdentificationNumber,
            String name,
            String normalizedNumber) {

        Company company = new Company(
                taxIdentificationNumber,
                name,
                postalAddress);

        String expected = "Company{\n"
                + "\t\t\t  taxIdentificationNumber = "
                + normalizedNumber + ", "
                + " name = " + name + ",\n"
                + "\t\t\t  address = " + postalAddress + "}";

        assertThat(company.toString(), is(expected));
    }

    private static Stream<Arguments> companyConstructorArguments() {
        return Stream.of(
                Arguments.of("123-456-78-90", "NameOne", "1234567890"),
                Arguments.of("234 567 89 01", "Name Two", "2345678901"),
                Arguments.of("345-6789012", "Name 3", "3456789012"),
                Arguments.of("456.789.01.23", "Name of Fourth Company inc.",
                        "4567890123")
        );
    }

    private static Stream<Arguments> notEqualsArguments() {
        return Stream.of(
                Arguments.of("9999999999", "NameOne"),
                Arguments.of("123-456-78-90", "NameOne changed")
        );
    }

    private static Stream<Arguments> companyConstructorNullArguments() {
        return Stream.of(
                Arguments.of(null, "NameOne", "taxIdentificationNumber"),
                Arguments.of("123-456-78-90", null, "name")
        );
    }
}
