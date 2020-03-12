package pl.CodersTrust.invoice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.CodersTrust.invoice.model.Invoice;


import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class InvoiceApplication {

    public static void main(final String[] args) {
        for (int i = 0; i < 100; i++) {
            Invoice test = new Invoice(null, null, LocalDate.now(), new ArrayList<>());
            System.out.println(test.getId());
        }
    }
}
