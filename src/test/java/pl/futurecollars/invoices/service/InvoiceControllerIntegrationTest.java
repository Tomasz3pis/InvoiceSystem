package pl.futurecollars.invoices.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceOne;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceThree;
import static pl.futurecollars.invoices.providers.TestInvoiceProvider.getInvoiceTwo;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;


class InvoiceControllerIntegrationTest extends IntegrationTestBase {

    @BeforeEach
    void setup() {
        List<Invoice> invoices = invoiceService.getInvoices();
        for (Invoice invoice : invoices) {
            invoiceService.deleteInvoice(invoice.getId());
        }
    }

    @ParameterizedTest
    @MethodSource("testInvoices")
    void shouldSaveAnInvoice(Invoice invoice) throws Exception {
        // given

        // when
        String id = mockMvc.perform(post(URL_TEMPLATE)
                .content(json(invoice))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        mockMvc.perform(get(URL_TEMPLATE + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(id))))
                .andExpect(jsonPath("$.issueDate", is(invoice.getIssueDate().toString())))
                .andExpect(jsonPath("$.saleDate", is(invoice.getSaleDate().toString())))
                .andExpect(jsonPath("$.seller.taxIdentificationNumber",
                        is(invoice.getSeller().getTaxIdentificationNumber())))
                .andExpect(jsonPath("$.seller.name", is(invoice.getSeller().getName())))
                .andExpect(jsonPath("$.seller.address.streetName",
                        is(invoice.getSeller().getAddress().getStreetName())))
                .andExpect(jsonPath("$.seller.address.streetNumber",
                        is(invoice.getSeller().getAddress().getStreetNumber())))
                .andExpect(jsonPath("$.seller.address.apartmentNumber",
                        is(invoice.getSeller().getAddress().getApartmentNumber())))
                .andExpect(jsonPath("$.seller.address.postalCode",
                        is(invoice.getSeller().getAddress().getPostalCode())))
                .andExpect(jsonPath("$.seller.address.city", is(invoice.getSeller().getAddress().getCity())))
                .andExpect(jsonPath("$.seller.taxIdentificationNumber",
                        is(invoice.getSeller().getTaxIdentificationNumber())))
                .andExpect(jsonPath("$.buyer.taxIdentificationNumber",
                        is(invoice.getBuyer().getTaxIdentificationNumber())))
                .andExpect(jsonPath("$.buyer.name", is(invoice.getBuyer().getName())))
                .andExpect(jsonPath("$.buyer.address.streetName", is(invoice.getBuyer().getAddress().getStreetName())))
                .andExpect(jsonPath("$.buyer.address.streetNumber",
                        is(invoice.getBuyer().getAddress().getStreetNumber())))
                .andExpect(jsonPath("$.buyer.address.apartmentNumber",
                        is(invoice.getBuyer().getAddress().getApartmentNumber())))
                .andExpect(jsonPath("$.buyer.address.postalCode", is(invoice.getBuyer().getAddress().getPostalCode())))
                .andExpect(jsonPath("$.buyer.address.city", is(invoice.getBuyer().getAddress().getCity())))
                .andExpect(jsonPath("$.buyer.taxIdentificationNumber",
                        is(invoice.getBuyer().getTaxIdentificationNumber())))
                .andExpect(jsonPath("$.entries.[0].itemName", is(invoice.getEntries().get(0).getItemName())))
                .andExpect(jsonPath("$.entries.[0].quantity", is(invoice.getEntries().get(0).getQuantity())))
                .andExpect(jsonPath("$.entries.[0].netPrice",
                        is(invoice.getEntries().get(0).getNetPrice().doubleValue())))
                .andExpect(jsonPath("$.entries.[0].vat", is(invoice.getEntries().get(0).getVat().toString())));

        mockMvc.perform(delete(URL_TEMPLATE + "/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400givenInvalidInvoiceObject() throws Exception {
        // given
        Invoice invoice = getInvoiceOne();
        invoice.getSeller().getAddress().setStreetName("");
        invoice.getSeller().getAddress().setStreetNumber("");
        invoice.getSeller().getAddress().setPostalCode("");
        invoice.getSeller().getAddress().setCity("");
        invoice.getEntries().get(0).setItemName("");
        invoice.getEntries().get(0).setNetPrice(null);
        invoice.getEntries().get(0).setVat(null);

        // when

        // then
        mockMvc.perform(post(URL_TEMPLATE)
                .content(json(invoice))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", containsString("Input validation failed with message:")))
                .andExpect(jsonPath("$", containsString("streetName: must not be blank")))
                .andExpect(jsonPath("$", containsString("streetNumber: must not be blank")))
                .andExpect(jsonPath("$", containsString("city: must not be blank")))
                .andExpect(jsonPath("$", containsString("postalCode: must not be blank")))
                .andExpect(jsonPath("$", containsString("itemName: must not be blank")))
                .andExpect(jsonPath("$", containsString("netPrice: must not be null")))
                .andExpect(jsonPath("$", containsString("vat: must not be null")));
    }

    @ParameterizedTest
    @MethodSource("getInvoiceByDateArguments")
    void shouldGetListOfInvoicesByDate(LocalDate startDate, LocalDate endDate, int expectedInvoiceCount)
            throws Exception {
        // given
        Invoice invoice = getInvoiceOne();
        postInvoicesForEachMonth(invoice);

        // when

        // then
        String responseBody = mockMvc.perform(get(URL_TEMPLATE + getRequestParams(startDate, endDate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expectedInvoiceCount)))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Invoice> invoices = mapper.readValue(responseBody, new TypeReference<List<Invoice>>() {
        });

        for (int i = 0; i < expectedInvoiceCount; i++) {
            if (startDate == null && endDate == null) {
                assertThat(invoices.get(i).getIssueDate().getClass(), is(LocalDate.class));
            } else if (startDate == null) {
                assertTrue(invoices.get(i).getIssueDate().isBefore(endDate.plusDays(1)));
            } else if (endDate == null) {
                assertTrue(invoices.get(i).getIssueDate().isAfter(startDate.minusDays(1)));
            } else {
                assertTrue(invoices.get(i).getIssueDate().isAfter(startDate.minusDays(1)));
                assertTrue(invoices.get(i).getIssueDate().isBefore(endDate.plusDays(1)));
            }
        }
    }

    @Test
    void getInvoiceByIdShouldReturn404() throws Exception {
        // given

        // when

        // then
        mockMvc.perform(get(URL_TEMPLATE + "/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$",
                        is("Invoice with provided id does not exist in database. Invoice id: 999 not found.")));
    }

    @Test
    void shouldUpdateInvoice() throws Exception {
        // given
        Invoice invoiceOne = getInvoiceOne();
        Invoice invoiceTwo = getInvoiceTwo();

        String id = mockMvc.perform(post(URL_TEMPLATE)
                .content(json(invoiceOne))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // when
        mockMvc.perform(put(URL_TEMPLATE + "/" + id)
                .content(json(invoiceTwo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        mockMvc.perform(get(URL_TEMPLATE + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(id))))
                .andExpect(jsonPath("$.seller.taxIdentificationNumber",
                        is(invoiceTwo.getSeller().getTaxIdentificationNumber())));

        mockMvc.perform(delete(URL_TEMPLATE + "/" + id))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> testInvoices() {
        return Stream.of(
                Arguments.of(getInvoiceOne()),
                Arguments.of(getInvoiceTwo()),
                Arguments.of(getInvoiceThree())
        );
    }

    private static Stream<Arguments> getInvoiceByDateArguments() {
        return Stream.of(
                Arguments.of(null, null, 12),
                Arguments.of(LocalDate.parse("2019-01-01"), null, 12),
                Arguments.of(LocalDate.parse("2019-02-01"), null, 11),
                Arguments.of(LocalDate.parse("2019-03-01"), null, 10),
                Arguments.of(LocalDate.parse("2019-04-01"), null, 9),
                Arguments.of(LocalDate.parse("2019-05-01"), null, 8),
                Arguments.of(LocalDate.parse("2019-06-01"), null, 7),
                Arguments.of(LocalDate.parse("2019-07-01"), null, 6),
                Arguments.of(LocalDate.parse("2019-08-01"), null, 5),
                Arguments.of(LocalDate.parse("2019-09-01"), null, 4),
                Arguments.of(LocalDate.parse("2019-10-01"), null, 3),
                Arguments.of(LocalDate.parse("2019-11-03"), null, 2),
                Arguments.of(LocalDate.parse("2019-12-02"), null, 1),
                Arguments.of(LocalDate.parse("2019-12-30"), null, 0),
                Arguments.of(null, LocalDate.parse("2020-01-01"), 12),
                Arguments.of(null, LocalDate.parse("2019-06-30"), 6),
                Arguments.of(null, LocalDate.parse("2019-01-02"), 1),
                Arguments.of(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01"), 12),
                Arguments.of(LocalDate.parse("2019-02-01"), LocalDate.parse("2020-01-01"), 11),
                Arguments.of(LocalDate.parse("2019-05-01"), LocalDate.parse("2020-01-01"), 8),
                Arguments.of(LocalDate.parse("2019-05-30"), LocalDate.parse("2020-01-01"), 7),
                Arguments.of(LocalDate.parse("2019-05-30"), LocalDate.parse("2019-11-30"), 6)
        );
    }

    private void postInvoicesForEachMonth(Invoice invoice) throws Exception {
        for (int i = 1; i <= 12; i++) {
            invoice.setIssueDate(LocalDate.of(2019, i, i));
            mockMvc.perform(post("/invoices").content(json(invoice)).contentType(MediaType.APPLICATION_JSON));
        }
    }

    private String getRequestParams(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return "";
        }
        if (startDate == null) {
            return "?endDate=" + endDate.toString();
        }
        if (endDate == null) {
            return "?startDate=" + startDate.toString();
        }
        return "?startDate=" + startDate.toString() + "&endDate=" + endDate.toString();
    }
}
