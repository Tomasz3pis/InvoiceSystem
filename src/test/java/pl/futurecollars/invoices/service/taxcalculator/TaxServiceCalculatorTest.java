package pl.futurecollars.invoices.service.taxcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.futureCollars;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForFruits;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForGames;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForSoftWare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.model.Invoice;
import pl.futurecollars.invoices.service.invoice.InvoiceService;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TaxServiceCalculatorTest {

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private TaxCalculatorService taxCalculatorService;

    @Test
    void shouldCalculateOutcomeVat() {
        //Given
        BigDecimal expectedTaxResult = BigDecimal.valueOf(0);

        //When
        BigDecimal actualTaxResult = taxCalculatorService.calculateOutcomeVat(invoiceService.getInvoices(),
                futureCollars().getTaxIdentificationNumber());

        //Then
        assertEquals(actualTaxResult, expectedTaxResult);
    }

    @Test
    void shouldCalculateIncomeVat() {
        //Given
        BigDecimal expectedTaxResult = BigDecimal.valueOf(0);

        //When
        BigDecimal actualTaxResult = taxCalculatorService.calculateIncomeVat(invoiceService.getInvoices(),
                futureCollars().getTaxIdentificationNumber());

        //then
        assertEquals(actualTaxResult, expectedTaxResult);
    }

    @Test
    void shouldCalculateCosts() {
        //Given
        BigDecimal expectedTaxResult = BigDecimal.valueOf(0);

        //When
        BigDecimal actualTaxResult = taxCalculatorService.calculateCosts(invoiceService.getInvoices(),
                futureCollars().getTaxIdentificationNumber());

        //then
        assertEquals(actualTaxResult, expectedTaxResult);
    }

    @Test
    void shouldCalculateIncomeToCosts() {
        //Given
        BigDecimal expectedTaxResult = BigDecimal.valueOf(0);

        //When
        BigDecimal actualTaxResult = taxCalculatorService.calculateIncomeToCosts(invoiceService.getInvoices(),
                futureCollars().getTaxIdentificationNumber());

        //then
        assertEquals(actualTaxResult, expectedTaxResult);
    }

    @Test
    void shouldCalculateIncome() {
        //Given
        BigDecimal expectedTaxResult = BigDecimal.valueOf(0);

        //When
        BigDecimal actualTaxResult = taxCalculatorService.calculateIncome(invoiceService.getInvoices(),
                futureCollars().getTaxIdentificationNumber());

        //then
        assertEquals(actualTaxResult, expectedTaxResult);
    }

    @Test
    void shouldCalculateIncomeVatOnStubInvoiceList() {
        //Given
        List<Invoice> invoiceList = List.of(getInvoiceForGames(),
                getInvoiceForSoftWare());
        String companyBuyMoreid = companyBuyMore().getTaxIdentificationNumber();

        //when
        BigDecimal expectedResult = BigDecimal.valueOf(1668.5);
        BigDecimal actualRestult = taxCalculatorService.calculateIncome(invoiceList, companyBuyMoreid);

        //then
        assertEquals(expectedResult, actualRestult);
    }

    @Test
    void shouldCalculateOutcomeVatOnStubInvoiceList() {
        //Given
        List<Invoice> invoiceList = List.of(getInvoiceForGames(),
                getInvoiceForSoftWare());
        String futureCollars = futureCollars().getTaxIdentificationNumber();

        //when
        BigDecimal expectedResult = BigDecimal.valueOf(7045.5);
        BigDecimal actualRestult = taxCalculatorService.calculateOutcomeVat(invoiceList, futureCollars);

        //then
        assertEquals(expectedResult, actualRestult);
    }

    @Test
    void shouldCalculateCostsOnStubInvoiceList() {
        //Given
        List<Invoice> invoiceList = List.of(getInvoiceForGames(),
                getInvoiceForFruits());
        String futureCollars = futureCollars().getTaxIdentificationNumber();

        //when
        BigDecimal expectedResult = BigDecimal.valueOf(9070.2);
        BigDecimal actualRestult = taxCalculatorService.calculateCosts(invoiceList, futureCollars);

        //then
        assertEquals(expectedResult, actualRestult);
    }

    @Test
    void shouldCalculateIncomeOnStubInvoiceList() {
        //Given
        List<Invoice> invoiceList = List.of(getInvoiceForGames(),
                getInvoiceForFruits());
        String futureCollars = futureCollars().getTaxIdentificationNumber();

        //when
        BigDecimal expectedResult = BigDecimal.valueOf(210.0);
        BigDecimal actualRestult = taxCalculatorService.calculateIncome(invoiceList, futureCollars);

        //then
        assertEquals(expectedResult, actualRestult);
    }

    @Test
    void shouldCalculateIncomeToCostsOnStubInvoiceList() {
        //Given
        List<Invoice> invoiceList = List.of(getInvoiceForGames(),
                getInvoiceForFruits());
        String futureCollars = futureCollars().getTaxIdentificationNumber();

        //when
        BigDecimal expectedResult = BigDecimal.valueOf(-8860.2);
        BigDecimal actualRestult = taxCalculatorService.calculateIncomeToCosts(invoiceList, futureCollars);

        //then
        assertEquals(expectedResult, actualRestult);
    }

}
