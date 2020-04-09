package pl.futurecollars.invoices.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.futurecollars.invoices.model.Vat.VAT_0;
import static pl.futurecollars.invoices.model.Vat.VAT_23;
import static pl.futurecollars.invoices.model.Vat.VAT_5;
import static pl.futurecollars.invoices.model.Vat.VAT_7;
import static pl.futurecollars.invoices.model.Vat.VAT_8;
import static pl.futurecollars.invoices.model.Vat.VAT_ZW;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class VatTest {

    @ParameterizedTest
    @MethodSource("vatEnumArguments")
    void shouldReturnVatRate(Vat vat, BigDecimal expectedVatRate) {
        // Given

        // When

        // Then
        assertThat(vat.getRate(), is(expectedVatRate));
    }

    private static Stream<Arguments> vatEnumArguments() {
        return Stream.of(
                Arguments.of(VAT_23, BigDecimal.valueOf(23)),
                Arguments.of(VAT_8, BigDecimal.valueOf(8)),
                Arguments.of(VAT_7, BigDecimal.valueOf(7)),
                Arguments.of(VAT_5, BigDecimal.valueOf(5)),
                Arguments.of(VAT_0, BigDecimal.valueOf(0)),
                Arguments.of(VAT_ZW, BigDecimal.valueOf(0))
        );
    }
}
