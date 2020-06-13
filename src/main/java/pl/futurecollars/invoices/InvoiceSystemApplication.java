package pl.futurecollars.invoices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.futurecollars.invoices.database.company.MongoDatabase;

@SpringBootApplication
public class InvoiceSystemApplication {

    public static void main(final String[] args) throws Exception {
        MongoDatabase.initMongoDb();
        SpringApplication.run(InvoiceSystemApplication.class, args);
    }
}
