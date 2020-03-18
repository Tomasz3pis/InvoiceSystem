package pl.futurecollars.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.futurecollars.invoice.model.CompanyProvider;
import pl.futurecollars.invoice.model.InvoiceProvider;
import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class InvoiceApplication {
    public static void main(final String[] args) throws IOException {
        SpringApplication.run(InvoiceApplication.class, args);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

        CompanyProvider buyer = new CompanyProvider.Builder()
                .withName("KoronaWirus Sp.z o.o.")
                .withTaxIdentificationNumber("0987654321")
                .withTown("Warsaw")
                .withPostCode("05-550")
                .withStreetName("Zakaźna")
                .build();
        CompanyProvider seller = new CompanyProvider.Builder()
                .withName("Emeryci")
                .withPostCode("21-500")
                .withTown("Biała Podlaska")
                .withStreetName("Jana Kazimierza")
                .withTaxIdentificationNumber("123567890")
                .build();

        InvoiceProvider invoiceProvider = new InvoiceProvider.Builder()
                .withId("00001")
                .withIssueDate(LocalDate.now())
                .withEntries(null)
                .withBuyer(buyer)
                .withSeller(seller)
                .build();
    }

}
