package pl.futurecollars.invoice.dto;

import pl.futurecollars.invoice.model.InvoiceEntry;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private Date date;
    private Long sellerID;
    private Long buyerID;
    @NotEmpty
    private List<InvoiceEntry> entries;

    public InvoiceDTO(Long id, Date date, Long seller, Long buyer, @NotEmpty List<InvoiceEntry> entries) {
        this.id = id;
        this.date = date;
        this.sellerID = seller;
        this.buyerID = buyer;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSeller() {
        return sellerID;
    }

    public void setSeller(Long seller) {
        this.sellerID = seller;
    }

    public Long getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Long buyerID) {
        this.buyerID = buyerID;
    }

    public List<InvoiceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntry> entries) {
        this.entries = entries;
    }
}
