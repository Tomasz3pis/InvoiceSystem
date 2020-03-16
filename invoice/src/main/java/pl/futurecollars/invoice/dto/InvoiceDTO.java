package pl.futurecollars.invoice.dto;

import pl.futurecollars.invoice.model.InvoiceEntry;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private LocalDate date;
    private Long seller;
    private Long buyer;
    @NotEmpty
    private List<InvoiceEntry> entries;

    public InvoiceDTO(Long id, LocalDate date, Long seller, Long buyer, @NotEmpty List<InvoiceEntry> entries) {
        this.id = id;
        this.date = date;
        this.seller = seller;
        this.buyer = buyer;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }
}
