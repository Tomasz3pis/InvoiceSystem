package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class PostalAddressTest {

    @ParameterizedTest
    @MethodSource("addressConstructorArguments")
    void shouldConstructAddressGivenData(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        PostalAddress postalAddress = new PostalAddress(
                streetName,
                streetNumber,
                apartmentNumber,
                postalCode,
                city);

        assertThat(postalAddress.getStreetName(), is(streetName));
        assertThat(postalAddress.getStreetNumber(), is(streetNumber));
        assertThat(postalAddress.getApartmentNumber(), is(apartmentNumber));
        assertThat(postalAddress.getPostalCode(), is(postalCode));
        assertThat(postalAddress.getCity(), is(city));
    }

    @ParameterizedTest
    @MethodSource("addressConstructorArguments")
    void shouldSetAddressFields(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        PostalAddress postalAddress = new PostalAddress(
                "",
                "",
                "",
                "00-000",
                "");

        postalAddress.setStreetName(streetName);
        postalAddress.setStreetNumber(streetNumber);
        postalAddress.setApartmentNumber(apartmentNumber);
        postalAddress.setPostalCode(postalCode);
        postalAddress.setCity(city);

        assertThat(postalAddress.getStreetName(), is(streetName));
        assertThat(postalAddress.getStreetNumber(), is(streetNumber));
        assertThat(postalAddress.getApartmentNumber(), is(apartmentNumber));
        assertThat(postalAddress.getPostalCode(), is(postalCode));
        assertThat(postalAddress.getCity(), is(city));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0",
            "1",
            "a",
            "A",
            "123-456",
            "1-2345",
            "12345",
            "12-34",
            "12-3",
            "ab-cde",
            "12–345",
            "12.345",
            "12=345",
            "12,345"
    })
    void shouldThrowExceptionGivenWrongPostalCodeFormat(String postalCode) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new PostalAddress(
                        "",
                        "",
                        "",
                        postalCode,
                        ""));
        assertThat(exception.getMessage(), is("Provided postalCode: "
                + postalCode
                + " does not match the pattern '00-000'"));
    }

    @ParameterizedTest
    @MethodSource("addressConstructorNullArguments")
    void shouldThrowExceptionGivenNullArgument(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city,
            String nullObjectName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new PostalAddress(
                        streetName,
                        streetNumber,
                        apartmentNumber,
                        postalCode,
                        city));
        assertThat(exception.getMessage(), is("Provided "
                + nullObjectName
                + " Object cannot be null"));
    }

    @ParameterizedTest
    @MethodSource("addressConstructorArguments")
    void shouldReturnTrueForEqualAddresses(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        PostalAddress firstAddress = new PostalAddress(
                streetName,
                streetNumber,
                apartmentNumber,
                postalCode,
                city);
        PostalAddress secondAddress = new PostalAddress(
                streetName,
                streetNumber,
                apartmentNumber,
                postalCode,
                city);

        assertThat(firstAddress.equals(secondAddress), is(true));
        assertThat(firstAddress.equals(firstAddress), is(true));
        assertThat(secondAddress.equals(secondAddress), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseForDifferentAddresses(
            PostalAddress firstAddress, PostalAddress secondAddress) {

        assertThat(firstAddress.equals(secondAddress), is(false));
        assertThat(firstAddress.equals("otherClassObject"), is(false));
        assertThat(firstAddress.equals(null), is(false));
    }

    @ParameterizedTest
    @MethodSource("addressConstructorArguments")
    void shouldReturnHashCodeGivenAddress(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        PostalAddress address = new PostalAddress(
                streetName,
                streetNumber,
                apartmentNumber,
                postalCode,
                city);
        PostalAddress secondAddress = new PostalAddress(
                "SomeOtherName",
                streetNumber,
                apartmentNumber,
                postalCode,
                city);
        assertNotEquals(0, address.hashCode());
        assertNotEquals(address.hashCode(), secondAddress.hashCode());
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnStringGivenAddress(PostalAddress address) {
        assertNotEquals(null, address.toString());
        assertNotEquals("", address.toString());
    }

    private static Stream<Arguments> addressConstructorArguments() {
        return Stream.of(
                Arguments.of(
                        "Ulica Pierwsza",
                        "101",
                        "1",
                        "01-111",
                        "Pierwszyce"),
                Arguments.of(
                        "Ulica Druga",
                        "2/4",
                        "2",
                        "22-202",
                        "Wólka Druga"),
                Arguments.of(
                        "Trzeciego Testu",
                        "33",
                        "",
                        "03-993",
                        "Trzeciorzędowice"),
                Arguments.of(
                        "Czwarta",
                        "4 B/C/D",
                        "4a",
                        "04-004",
                        "Czwartaki")
        );
    }

    private static Stream<Arguments> notEqualsArguments() {
        return Stream.of(
                Arguments.of(
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"),
                        new PostalAddress(
                                "Ulica Pierwsza zmieniona",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"
                        )
                ),
                Arguments.of(
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"),
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101 zmieniony",
                                "1",
                                "01-111",
                                "Pierwszyce"
                        )
                ),
                Arguments.of(
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"),
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1 zmieniony",
                                "01-111",
                                "Pierwszyce"
                        )
                ),
                Arguments.of(
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"),
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "99-999",
                                "Pierwszyce"
                        )
                ),
                Arguments.of(
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"),
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce zmienione"
                        )
                ),
                Arguments.of(
                        new PostalAddress(
                                "Ulica Pierwsza",
                                "101",
                                "1",
                                "01-111",
                                "Pierwszyce"),
                        new PostalAddress(
                                "Ulica Druga",
                                "2/4",
                                "2",
                                "22-202",
                                "Wólka Druga"
                        )
                )
        );
    }

    private static Stream<Arguments> addressConstructorNullArguments() {
        return Stream.of(
                Arguments.of(
                        null,
                        "101",
                        "1",
                        "01-111",
                        "Pierwszyce",
                        "streetName"),
                Arguments.of(
                        "Ulica Pierwsza",
                        null,
                        "1",
                        "01-111",
                        "Pierwszyce",
                        "streetNumber"),
                Arguments.of(
                        "Ulica Pierwsza",
                        "101",
                        null,
                        "01-111",
                        "Pierwszyce",
                        "apartmentNumber"),
                Arguments.of(
                        "Ulica Pierwsza",
                        "101",
                        "1",
                        null,
                        "Pierwszyce",
                        "postalCode"),
                Arguments.of(
                        "Ulica Pierwsza",
                        "101",
                        "1",
                        "01-111",
                        null,
                        "city")
        );
    }
}
