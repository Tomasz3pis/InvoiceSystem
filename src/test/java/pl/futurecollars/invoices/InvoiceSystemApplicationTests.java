package pl.futurecollars.invoices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InvoiceSystemApplicationTests {

    @Test
    void applicationStarts() throws Exception {
        InvoiceSystemApplication.main(new String[] {});
    }
}
