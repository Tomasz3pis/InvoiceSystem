
package pl.futurecollars.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.futurecollars.invoice.model.CompanyProvider;
import pl.futurecollars.invoice.model.InvoiceProvider;
import java.time.LocalDate;

public class Main {

    public static void main(final String[] args) {
        ObjectMapper mapper = new ObjectMapper();

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
