package pl.futurecollars.invoices.database;

import pl.futurecollars.invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDatabase implements Database {

    @Override
    public List<Invoice> getInvoices(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return getInvoices();
        }
        final LocalDate finalStartDate = prepareStartDate(startDate);
        final LocalDate finalEndDate = prepareEndDate(endDate);

        return getInvoices().stream()
                .filter(invoice -> invoice.getIssueDate()
                        .isAfter(finalStartDate))
                .filter(invoice -> invoice.getIssueDate()
                        .isBefore(finalEndDate))
                .collect(Collectors.toList());
    }

    private LocalDate prepareStartDate(LocalDate startDate) {
        if (startDate == null) {
            startDate = LocalDate.MIN.plusDays(1);
        }
        // Adding and subtracting day makes date inclusive
        return startDate.minusDays(1);
    }

    private LocalDate prepareEndDate(LocalDate endDate) {
        if (endDate == null) {
            endDate = LocalDate.MAX.minusDays(1);
        }
        // Adding and subtracting day makes date inclusive
        return endDate.plusDays(1);
    }
}
