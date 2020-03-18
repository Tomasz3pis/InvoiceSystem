package pl.futurecollars.invoice.model;

import java.time.LocalDate;
import java.util.List;

public class InvoiceProvider {

    public static class Builder {

        private String id;
        private LocalDate issueDate;
        private List<InvoiceEntry> entries;
        private InvoiceProvider invoice;
        private CompanyProvider company;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withIssueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder withSeller(CompanyProvider seller) {
            this.company = seller;
            return this;
        }

        public Builder withBuyer(CompanyProvider buyer) {
            this.company = buyer;
            return this;
        }

        public Builder withEntries(List<InvoiceEntry> entries) {
            this.entries = entries;
            return this;
        }

        public InvoiceProvider build() {
            return invoice;
        }
    }

}
