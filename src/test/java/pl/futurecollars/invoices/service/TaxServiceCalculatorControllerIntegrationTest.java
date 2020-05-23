package pl.futurecollars.invoices.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.futurecollars.invoices.model.Invoice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.futureCollars;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForFruits;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForGames;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceForSoftWare;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaxServiceCalculatorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxCalculatorController taxCalculatorController;

    @Autowired
    private InvoiceController invoiceController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(taxCalculatorController).isNotNull();
    }

    @BeforeEach
    public void setUpOfIncoiveList() {
        invoiceController.saveInvoice(getInvoiceForSoftWare());
        invoiceController.saveInvoice(getInvoiceForFruits());
        invoiceController.saveInvoice(getInvoiceForGames());
    }

    @AfterEach
    public void clearList() {
       for (Invoice invoice : invoiceController.getInvoices("2000-01-01", "2020-12-12")) {
           invoiceController.deleteInvoice(invoice.getId());
       }
    }

    @Test
    public void shouldReturnZeroIncomeVatForRandomCompany() throws Exception {
        mockMvc
                .perform(get("/companies/incomevat/" + Math.random()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    public void shouldReturnZeroOutcomeVatForRandomCompany() throws Exception {
        mockMvc
                .perform(get("/companies/outcomevat/" + Math.random()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    public void shouldReturnZeroIncomeForRandomCompany() throws Exception {
        mockMvc
                .perform(get("/companies/income/" + Math.random()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    public void shouldReturnZeroCostsForRandomCompany() throws Exception {
        mockMvc
                .perform(get("/companies/costs/" + Math.random()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    public void shouldReturnZeroIncomeToCostsForRandomCompany() throws Exception {
        mockMvc
                .perform(get("/companies/incometocosts/" + Math.random()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }


    @Test
    public void shouldReturnIncomeVATForFCompanyBuyMore() throws Exception {
        mockMvc
                .perform(get("/companies/incomevat/" + companyBuyMore().getTaxIdentificationNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("6270.2")));
    }

    @Test
    public void shouldReturnOutcomeVATForFutureCollars() throws Exception {
        mockMvc
                .perform(get("/companies/outcomevat/" + futureCollars().getTaxIdentificationNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("6270.2")));
    }

    @Test
    public void shouldReturnIncomeForBuyMorCompany() throws Exception {
        mockMvc
                .perform(get("/companies/income/" + companyBuyMore().getTaxIdentificationNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1017.4")));
    }

    @Test
    public void shouldReturnCostsForFutureCollars() throws Exception {
        mockMvc
                .perform(get("/companies/costs/" + futureCollars().getTaxIdentificationNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("6270.2")));
    }

    @Test
    public void shouldReturnIncomeToCostsForFutureCollars() throws Exception {
        mockMvc
                .perform(get("/companies/incometocosts/" + futureCollars().getTaxIdentificationNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("-6270.2")));
    }

    @Test
    public void shouldReturnExceptionForBlankTaxIdentificationNumber() throws Exception {
        mockMvc
                .perform(get("/companies/incomevat/ "))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Input validation failed with message:"
                        + " calculateIncomeVat.taxIdentificationNumber: Tax id is mandrator")));
    }

}
