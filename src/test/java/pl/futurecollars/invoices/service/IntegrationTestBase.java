package pl.futurecollars.invoices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

    protected static final String INVOICE_SERVICE_PATH = "/invoices";
    protected static final String COMPANY_SERVICE_PATH = "/companies";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected InvoiceService invoiceService;

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected ObjectMapper mapper;

    protected String json(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
