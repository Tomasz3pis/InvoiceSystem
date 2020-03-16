package pl.futurecollars.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class InvoiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void applicationStarts() throws IOException {
        InvoiceApplication.main(new String[]{});
    }

}


// make unit test(test for class)
//