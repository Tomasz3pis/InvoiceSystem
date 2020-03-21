package pl.futurecollars.invoice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.futurecollars.invoice.database.InMemoryInvoiceDatabase;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceEntry;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void test() throws Exception {

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
  /*
    Company seller = new Company("Firma1", "123");
        seller.setId(8080L);
    Company buyer = new Company("Firma2", "124");
        buyer.setId(8081L);
    Invoice savedInvoice = inMemoryInvoiceDatabase.save(new Invoice(null, new Date(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));
    MvcResult mvcResult = mockMvc.perform(get("/invoices/" + savedInvoice.getId())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();*/


}
