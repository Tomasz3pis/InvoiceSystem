package pl.futurecollars.invoices.service;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBestBuy;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuySome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.futurecollars.invoices.model.Company;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CompanyControllerIntegrationTest extends IntegrationTestBase {

    private static final String NOT_EXISTING_ID = "/888";

    @BeforeEach
    void setup() {
        for (Company company : companyService.getCompanies()) {
            companyService.deleteCompany(company.getId());
        }
    }

    @ParameterizedTest
    @MethodSource("testCompanies")
    void shouldSaveAnCompany(Company company) throws Exception {
        // given

        // when
        int companyId = Integer.parseInt(response(company));

        // then
        mockMvc.perform(get(COMPANY_SERVICE_PATH + "/" + companyId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((companyId))))
                .andExpect(jsonPath("$.taxIdentificationNumber",
                        is(company.getTaxIdentificationNumber())))
                .andExpect(jsonPath("$.name", is(company.getName())))
                .andExpect(jsonPath("$.address.streetName",
                        is(company.getAddress().getStreetName())))
                .andExpect(jsonPath("$.address.streetNumber",
                        is(company.getAddress().getStreetNumber())))
                .andExpect(jsonPath("$.address.apartmentNumber",
                        is(company.getAddress().getApartmentNumber())))
                .andExpect(jsonPath("$.address.postalCode",
                        is(company.getAddress().getPostalCode())))
                .andExpect(jsonPath("$.address.city",
                        is(company.getAddress().getCity())));
    }

    @Test
    void shouldDeleteSavedCompany() throws Exception {
        //given
        Company company = companyBuyMore();

        //when
        int companyId = Integer.parseInt(response(company));

        //then
        mockMvc.perform(delete(COMPANY_SERVICE_PATH + "/" + companyId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400GivenInvalidCompanyObject() throws Exception {
        // given
        Company company = companyBuyMore();
        company.setName("");
        company.setTaxIdentificationNumber("");

        // when

        // then
        mockMvc.perform(post(COMPANY_SERVICE_PATH)
                .content(json(company))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCompanyByIdShouldReturn404() throws Exception {
        // given

        // when

        // then
        mockMvc.perform(get(COMPANY_SERVICE_PATH + NOT_EXISTING_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateCompany() throws Exception {
        // given
        Company companyOne = companyBuyMore();
        Company companyTwo = companyBestBuy();

        int companyId = Integer.parseInt(response(companyOne));

        // when
        mockMvc.perform(put(COMPANY_SERVICE_PATH + "/" + companyId)
                .content(json(companyTwo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        mockMvc.perform(get(COMPANY_SERVICE_PATH + "/" + companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(companyId)))
                .andExpect(jsonPath("$.taxIdentificationNumber",
                        is(companyTwo.getTaxIdentificationNumber())));
    }

    private String response(Company company) throws Exception {
        return mockMvc.perform(post(COMPANY_SERVICE_PATH)
                .content(json(company))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private static Stream<Arguments> testCompanies() {
        return Stream.of(
                Arguments.of(companyBuyMore()),
                Arguments.of(companyBuySome()),
                Arguments.of(companyBestBuy())
        );
    }
}
