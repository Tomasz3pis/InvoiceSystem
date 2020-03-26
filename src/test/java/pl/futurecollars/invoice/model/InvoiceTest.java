package pl.futurecollars.invoice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import com.jparams.verifier.tostring.ToStringVerifier;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.futurecollars.invoice.model.Invoice.InvoiceBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class InvoiceTest {

    private Company company = mock(Company.class);
    private InvoiceEntry entry = mock(InvoiceEntry.class);

    @Test
    void shouldReturnSetSeller() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();

        //when
        invoice.setSeller(company);

        //then
        assertEquals(invoice.getSeller(), company);
    }

    @Test
    void shouldReturnSetBuyer() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        //when
        invoice.setBuyer(company);

        //then
        assertEquals(invoice.getBuyer(), company);
    }

    @Test
    void shouldReturnSetEntries() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        //when
        invoice.setEntries(List.of(entry));

        //then
        assertEquals(invoice.getEntries(), List.of(entry));
    }

    @Test
    void shouldReturnSetDate() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        //when
        invoice.setData(LocalDate.of(2015, 12, 14));

        //then
        assertEquals(invoice.getData(), LocalDate.of(2015, 12, 14));
    }

    @Test
    void shouldCompareTwoDifferentHashCodes() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        //when
        Invoice secondInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now().minusDays(5))
                .withEntries(new ArrayList<>())
                .build();
        //then
        assertNotEquals(invoice.hashCode(), secondInvoice.hashCode());
    }

    @Test
    void shouldCheckIfToStringIsNotEmptyOrNull() {
        //given

        //when

        //then
        ToStringVerifier.forClass(Invoice.class)
                .verify();
    }

    @Test
    void shouldReturnHashCode() {
        //given
        Invoice invoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        //when
        int actual = invoice.hashCode();

        //then
        assertEquals(actual, HashCodeBuilder.reflectionHashCode(invoice));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void shouldVerifyIfEqualWorksCorrectly(Invoice invoice, Invoice otherInvoice, boolean expected) {
        //given

        //when
        boolean actual = invoice.equals(otherInvoice);

        //then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> dataProvider() {

        Invoice firstInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now())
                .withEntries(new ArrayList<>())
                .build();
        Invoice secondInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now().minusDays(5))
                .withEntries(new ArrayList<>())
                .build();
        Invoice thirdInvoice = new InvoiceBuilder()
                .withBuyer(null)
                .withSeller(null)
                .withDate(LocalDate.now().minusDays(5))
                .withEntries(new ArrayList<>())
                .build();

        return Stream.of(
                Arguments.of(firstInvoice, secondInvoice, false),
                Arguments.of(secondInvoice, thirdInvoice, true),
                Arguments.of(firstInvoice, null, false)
        );
    }
}
