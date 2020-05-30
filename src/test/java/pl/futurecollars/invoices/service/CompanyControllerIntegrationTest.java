package pl.futurecollars.invoices.service;


import static org.hamcrest.Matchers.containsString;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.stream.Stream;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CompanyControllerIntegrationTest {

    private static final String COMPANY_SERVICE_PATH = "/companies";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyController companyController;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ObjectMapper mapper;

    private String json(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    @BeforeEach
    void setup() {
        List<Company> companies = companyService.getCompanies();
        for (Company company : companies) {
            companyService.deleteCompany(company.getId());
        }
    }

    @ParameterizedTest
    @MethodSource("testCompanies")
    void shouldSaveAnCompany(Company company) throws Exception {
        // given

        // when
        String companyId = mockMvc.perform(post(COMPANY_SERVICE_PATH)
                .content(json(company))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        mockMvc.perform(get(COMPANY_SERVICE_PATH + "/" + Integer.valueOf(companyId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((Integer.valueOf(companyId)))))
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


        mockMvc.perform(delete(COMPANY_SERVICE_PATH + "/" + Integer.valueOf(companyId)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400givenInvalidCompanyObject() throws Exception {
        // given
        Company company = companyBuyMore();
        company.setName("");
        company.setTaxIdentificationNumber("");

        // when

        // then
        mockMvc.perform(post(COMPANY_SERVICE_PATH)
                .content(json(company))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", containsString("Input validation failed with message:")))
                .andExpect(jsonPath("$", containsString("name: must not be blank")))
                .andExpect(jsonPath("$", containsString("taxIdentificationNumber: must not be blank")));
    }

    @Test
    void getCompanyByIdShouldReturn404() throws Exception {
        // given

        // when

        // then
        mockMvc.perform(get(COMPANY_SERVICE_PATH + "/888"))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldUpdateCompany() throws Exception {
        // given
        Company companyOne = companyBuyMore();
        Company companyTwo = companyBestBuy();

        String companyId = mockMvc.perform(post(COMPANY_SERVICE_PATH)
                .content(json(companyOne))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // when
        mockMvc.perform(put(COMPANY_SERVICE_PATH + "/" + Integer.valueOf(companyId))
                .content(json(companyTwo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        mockMvc.perform(get(COMPANY_SERVICE_PATH + "/" + Integer.valueOf(companyId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(companyId))))
                .andExpect(jsonPath("$.taxIdentificationNumber",
                        is(companyTwo.getTaxIdentificationNumber())));

        mockMvc.perform(delete(COMPANY_SERVICE_PATH + "/" + Integer.valueOf(companyId)))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> testCompanies() {
        return Stream.of(
                Arguments.of(companyBuyMore()),
                Arguments.of(companyBuySome()),
                Arguments.of(companyBestBuy())
        );
    }
}
