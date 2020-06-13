package pl.futurecollars.invoices.service.taxcalculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.providers.TestCompanyProvider;
import pl.futurecollars.invoices.service.invoice.InvoiceService;
import pl.futurecollars.invoices.service.taxcalculator.TaxCalculatorService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForFruits;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForGames;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForGrocery;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaxServiceIntegrationTests {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private TaxCalculatorService taxCalculatorService;

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    @AfterEach
    void clearTheInvoice() {
        for (Invoice invoice : invoiceService.getInvoices()) {
            invoiceService.deleteInvoice(invoice.getId());
        }
    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldCaclulateIncomeVat(List<Invoice> invoices) {
        //Given
        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }

        //When
        BigDecimal actualVatValueInFutureCollarsCompany = taxCalculatorService
                .calculateIncomeVat(invoiceService.getInvoices(),
                        TestCompanyProvider.futureCollars().getTaxIdentificationNumber());

        //Then
        assertThat(actualVatValueInFutureCollarsCompany, is(BigDecimal.valueOf(10795.43)));

    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldCalculateOutComeVat(List<Invoice> invoices) {
        //Given
        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }

        //When
        BigDecimal actualVatValueInFutureCollarsCompany = taxCalculatorService
                .calculateOutcomeVat(invoiceService.getInvoices(),
                        TestCompanyProvider.futureCollars().getTaxIdentificationNumber());

        //Then
        assertThat(actualVatValueInFutureCollarsCompany, is(BigDecimal.valueOf(6270.2)));
    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldCalculateIncome(List<Invoice> invoices) {
        //Given
        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }

        //When
        BigDecimal actualVatValueInFutureCollarsCompany = taxCalculatorService
                .calculateIncome(invoiceService.getInvoices(),
                        TestCompanyProvider.futureCollars().getTaxIdentificationNumber());

        //Then
        assertThat(actualVatValueInFutureCollarsCompany, is(BigDecimal.valueOf(1357.77)));

    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldCalculateIncomeToCosts(List<Invoice> invoices) {
        //Given
        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }

        //When
        BigDecimal actualVatValueInFutureCollarsCompany = taxCalculatorService
                .calculateIncomeToCosts(invoiceService.getInvoices(),
                        TestCompanyProvider.futureCollars().getTaxIdentificationNumber());

        //Then
        assertThat(actualVatValueInFutureCollarsCompany, is(BigDecimal.valueOf(-15707.86)));

    }

    @ParameterizedTest
    @MethodSource("invoiceSystemTestArguments")
    void shouldCalculateCosts(List<Invoice> invoices) {
        //Given
        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }

        //When
        BigDecimal actualVatValueInFutureCollarsCompany = taxCalculatorService
                .calculateCosts(invoiceService.getInvoices(),
                        TestCompanyProvider.futureCollars().getTaxIdentificationNumber());

        //Then
        assertThat(actualVatValueInFutureCollarsCompany, is(BigDecimal.valueOf(17065.63)));
    }

    private static Stream<Arguments> invoiceSystemTestArguments() {
        List<Invoice> invoices = List.of(getInvoiceForGrocery(), getInvoiceForFruits(), getInvoiceForGames());
        return Stream.of(
                Arguments.of(invoices)
        );
    }

}
