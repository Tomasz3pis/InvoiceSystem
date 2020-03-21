
package pl.futurecollars.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class InvoiceApplication {
    public static void main(final String[] args) throws IOException {
        SpringApplication.run(InvoiceApplication.class, args);
    }

}
