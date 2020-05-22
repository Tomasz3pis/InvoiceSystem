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

    protected static final String URL_TEMPLATE = "/invoices";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected InvoiceController invoiceController;

    @Autowired
    protected InvoiceService invoiceService;

    @Autowired
    protected ObjectMapper mapper;

    protected String json(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
