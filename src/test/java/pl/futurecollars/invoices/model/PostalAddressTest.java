package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        // Given

        // When
        PostalAddress postalAddress = new PostalAddress(
                streetName,
                streetNumber,
                apartmentNumber,
                postalCode,
                city);

        // Then
        assertThat(postalAddress.getStreetName(), is(streetName));
        assertThat(postalAddress.getStreetNumber(), is(streetNumber));
        assertThat(postalAddress.getApartmentNumber(), is(apartmentNumber));
        assertThat(postalAddress.getPostalCode(), is(postalCode));
        assertThat(postalAddress.getCity(), is(city));
    }

    @ParameterizedTest
    @MethodSource("addressConstructorArguments")
    void shouldReturnTrueForEqualAddresses(
            String streetName,
            String streetNumber,
            String apartmentNumber,
            String postalCode,
            String city) {
        // Given

        // When
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

        // Then
        assertThat(firstAddress.equals(secondAddress), is(true));
        assertThat(firstAddress.equals(firstAddress), is(true));
        assertThat(secondAddress.equals(secondAddress), is(true));
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnFalseForDifferentAddresses(
            PostalAddress firstAddress, PostalAddress secondAddress) {
        // Given

        // When

        // Then
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
        // Given

        // When
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

        // Then
        assertNotEquals(0, address.hashCode());
        assertNotEquals(address.hashCode(), secondAddress.hashCode());
    }

    @ParameterizedTest
    @MethodSource("notEqualsArguments")
    void shouldReturnStringGivenAddress(PostalAddress address) {
        // Given

        // When

        // Then
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
}
