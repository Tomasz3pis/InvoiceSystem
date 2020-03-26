package pl.futurecollars.invoice;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.Invoice.InvoiceBuilder;
import pl.futurecollars.invoice.model.InvoiceEntry;
import pl.futurecollars.invoice.model.Vat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class InvoiceProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Company firstCompany = new Company(7896541234L, "St steerra", "GBC");
        Company secondCompany = new Company(9080081223L, "Bonsa st", "Unomi");
        InvoiceEntry firstEntry = new InvoiceEntry("do this", new BigDecimal(1500), Vat.VAT_23, 5);
        InvoiceEntry secondEntry = new InvoiceEntry("do that", new BigDecimal(600), Vat.VAT_8, 2);
        Invoice firstInvoice = new InvoiceBuilder()
                .withSeller(firstCompany)
                .withBuyer(secondCompany)
                .withDate(LocalDate.of(2019, 5, 18))
                .withEntries(List.of(firstEntry, secondEntry))
                .build();
        Invoice secondInvoice = new InvoiceBuilder()
                .withSeller(secondCompany)
                .withBuyer(firstCompany)
                .withDate(LocalDate.of(2019, 11, 8))
                .withEntries(List.of(firstEntry))
                .build();
        return Stream.of(Arguments.of(firstInvoice, secondInvoice));
    }
}
