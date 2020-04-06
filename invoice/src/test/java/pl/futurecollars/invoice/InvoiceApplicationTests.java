package pl.futurecollars.invoice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.futurecollars.invoice.database.InMemoryCompanyDatabase;
import pl.futurecollars.invoice.database.InMemoryInvoiceDatabase;
import pl.futurecollars.invoice.dto.InvoiceDTO;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceEntry;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InvoiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryInvoiceDatabase inMemoryInvoiceDatabase;

    @Autowired
    private InMemoryCompanyDatabase inMemoryCompanyDatabase;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @BeforeEach
    public void setUp() {
        inMemoryInvoiceDatabase.getInvoices().clear();
    }

    @Test
    public void getInvoicesList() throws Exception {

        // given
        Company seller = new Company("Firma1", "123");
        seller.setId(8080L);
        Company buyer = new Company("Firma2", "124");
        buyer.setId(8081L);
        Invoice savedInvoice = inMemoryInvoiceDatabase.save(new Invoice(null, new Date(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));
        Invoice savedInvoice2 = inMemoryInvoiceDatabase.save(new Invoice(null, new Date(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));

        MvcResult mvcResult = mockMvc.perform(get("/invoices")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Invoice> result = mapper.readValue(mvcResult.getResponse().getContentAsString(), mapper.getTypeFactory().constructCollectionType(List.class, Invoice.class));

        assertThat(result).containsExactly(savedInvoice, savedInvoice2);
    }


    @Test
    public void getInvoiceById() throws Exception {
        String uri = "/invoices";
        Company seller = new Company("Firma1", "123");
        seller.setId(8080L);
        Company buyer = new Company("Firma2", "124");
        buyer.setId(8081L);
        Invoice savedInvoice = inMemoryInvoiceDatabase.save(new Invoice(null, new Date(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri + "/" + savedInvoice.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn();


        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Invoice resultInvoice = mapFromJson(content, Invoice.class);
        assertEquals(resultInvoice, savedInvoice);
        assertEquals(resultInvoice.getId(), savedInvoice.getId());
    }

    @Test
    public void postInvoiceTes() throws Exception {
        String uri = "/invoices";
        Company seller = new Company("Firma1", "123");
        seller.setId(8080L);
        Company buyer = new Company("Firma2", "124");
        buyer.setId(8081L);
        seller = inMemoryCompanyDatabase.save(seller);
        buyer = inMemoryCompanyDatabase.save(buyer);
        InvoiceDTO invoiceToBeSaved = new InvoiceDTO(null, new Date(), seller.getId(), buyer.getId(), Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23"))));
        String jsonRequestBody = mapToJson(invoiceToBeSaved);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonRequestBody)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Invoice resultInvoice = mapFromJson(content, Invoice.class);
        assertNotNull(resultInvoice.getId());
    }

    @Test
    public void updateInvoice() throws Exception {
        String uri = "/invoices";
        Company seller = new Company("Firma1", "123");
        Company buyer = new Company("Firma2", "124");
        seller = inMemoryCompanyDatabase.save(seller);
        buyer = inMemoryCompanyDatabase.save(buyer);
        Invoice savedInvoice = inMemoryInvoiceDatabase.save(new Invoice(null, new Date(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));
        InvoiceEntry updatedEntry = new InvoiceEntry("Ksiazki", new BigDecimal("30.23"));
        InvoiceDTO updatedInvoice = (new InvoiceDTO(savedInvoice.getId(), new Date(), seller.getId(), buyer.getId(), Collections.singletonList(updatedEntry)));
        String jsonRequestBody = mapToJson(updatedInvoice);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonRequestBody)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Invoice resultInvoice = mapFromJson(content, Invoice.class);
        assertNotNull(resultInvoice.getId());
        assertEquals(savedInvoice.getId(), resultInvoice.getId());
        assertThat(resultInvoice.getBuyer()).isEqualTo(buyer);
        assertThat(resultInvoice.getEntries()).containsExactly(updatedEntry);
    }

    @Test
    public void deleteInvoice() throws Exception {
        String uri = "/invoices";
        Company seller = new Company("Firma1", "123");
        seller.setId(8080L);
        Company buyer = new Company("Firma2", "124");
        buyer.setId(8081L);
        Invoice savedInvoice = inMemoryInvoiceDatabase.save(new Invoice(null, new Date(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/" + savedInvoice.getId())).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        assertThat(inMemoryInvoiceDatabase.getInvoices()).isEmpty();
    }
}

