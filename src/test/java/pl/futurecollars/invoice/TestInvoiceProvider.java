package pl.futurecollars.invoice;

import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.model.Invoice;
import pl.futurecollars.invoice.model.Invoice.InvoiceBuilder;
import pl.futurecollars.invoice.model.InvoiceEntry;
import pl.futurecollars.invoice.model.Vat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestInvoiceProvider {

    public Company getFirstCompany() {
        return new Company(7896541234L, "St steerra", "GBC");
    }

    public Company getSecondCompany() {
        return new Company(9081233113L, "Uuki st", "Mijagi");
    }

    public Company getThirdCompany() {
        return new Company(9080081223L, "Bonsa st", "Unomi");
    }

    public Company getFourthCompany() {
        return new Company(9080081223L, "Bonsa st", "Unomi");
    }

    public InvoiceEntry getFirstEntry() {
        return new InvoiceEntry("do this", new BigDecimal(1500), Vat.VAT_23, 5);
    }

    public InvoiceEntry getSecondEntry() {
        return new InvoiceEntry("do that", new BigDecimal(600), Vat.VAT_8, 2);
    }

    public InvoiceEntry getThirdEntry() {
        return new InvoiceEntry("also this", new BigDecimal(650), Vat.VAT_5, 3);
    }

    public InvoiceEntry getFourthEntry() {
        return new InvoiceEntry("something", new BigDecimal(1100), Vat.VAT_ZW, 1);
    }

    public InvoiceEntry getFifthEntry() {
        return new InvoiceEntry("anything", new BigDecimal(100), Vat.VAT_0, 4);
    }

    public Invoice getInvoiceWith5Entries() {
        return new InvoiceBuilder()
                .withSeller(getFourthCompany())
                .withBuyer(getThirdCompany())
                .withDate(LocalDate.of(2010, 1, 11))
                .withEntries(List.of(getFirstEntry(), getSecondEntry(), getThirdEntry(), getFourthEntry(), getFifthEntry()))
                .build();
    }

    public Invoice getBaseInvoice() {
        return new InvoiceBuilder()
                .withSeller(getFirstCompany())
                .withBuyer(getSecondCompany())
                .withDate(LocalDate.of(2019, 5, 18))
                .withEntries(List.of(getFirstEntry(), getSecondEntry()))
                .build();
    }

    public Invoice getSameAsBaseInvoice() {
        return new InvoiceBuilder()
                .withSeller(getFirstCompany())
                .withBuyer(getSecondCompany())
                .withDate(LocalDate.of(2019, 5, 18))
                .withEntries(List.of(getFirstEntry(), getSecondEntry()))
                .build();
    }
}
