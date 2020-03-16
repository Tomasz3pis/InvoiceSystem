package pl.futurecollars.invoice;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.futurecollars.invoice.database.InMemoryInvoiceDatabase;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.InvoiceEntry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static java.net.NetworkInterface.getAll;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        Invoice savedInvoice = inMemoryInvoiceDatabase.save(new Invoice(null, LocalDate.now(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));


        mockMvc.perform(get("/invoices/" + savedInvoice.getId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.seller.name").value("Firma1"))
                .andExpect(jsonPath("$.seller.taxIdentificationNumber").value("123"))
                .andExpect(jsonPath("$.seller.id").value("8080"))
                .andExpect(jsonPath("$.buyer.name").value("Firma2"))
                .andExpect(jsonPath("$.buyer.taxIdentificationNumber").value("124"))
                .andExpect(jsonPath("$.buyer.id").value("8081"))
                .andExpect(jsonPath("$.entries.[0].title").value("Videokurs"))
                .andExpect(jsonPath("$.entries.[0].value").value("23.23"));
    }

//    @Test
//    public void test2() throws Exception {
//
//        // given
//        Company seller = new Company("Firma1", "123");
//        seller.setId(8080L);
//        Company buyer = new Company("Firma2", "124");
//        buyer.setId(8081L);
//        Invoice savedInvoice1 = inMemoryInvoiceDatabase.save(new Invoice(null, LocalDate.now(), seller, buyer, Collections.singletonList(new InvoiceEntry("Videokurs", new BigDecimal("23.23")))));
//		Invoice savedInvoice2 = inMemoryInvoiceDatabase.save(new Invoice(null, LocalDate.now(), seller, buyer, Collections.singletonList(new InvoiceEntry("Książki", new BigDecimal("30.67")))));
//
//
//
//		mockMvc.perform(get("/invoices/" + savedInvoice1.getId() + savedInvoice2.getId())
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").isNotEmpty())
//                .andExpect(jsonPath("$.seller.name").value("Firma1"))
//                .andExpect(jsonPath("$.seller.taxIdentificationNumber").value("123"))
//                .andExpect(jsonPath("$.seller.id").value("8080"))
//                .andExpect(jsonPath("$.buyer.name").value("Firma2"))
//                .andExpect(jsonPath("$.buyer.taxIdentificationNumber").value("124"))
//                .andExpect(jsonPath("$.buyer.id").value("8081"))
//                .andExpect(jsonPath("$.entries[0].title").value("Videokurs"))
//                .andExpect(jsonPath("$.entries[0].value").value("23.23"));
//
//    }


}
