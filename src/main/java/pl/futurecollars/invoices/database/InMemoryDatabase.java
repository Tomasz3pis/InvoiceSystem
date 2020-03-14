package pl.futurecollars.invoices.database;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.helpers.CheckIdFormat.checkIdFormat;

import pl.futurecollars.invoices.model.Invoice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryDatabase implements Database {

    private List<Invoice> invoices = new ArrayList<>();
    private LinkedList<String> idNumbers = new LinkedList<>();

    @Override
    public void saveInvoice(Invoice invoice) {
        checkForNull(invoice, "invoice");
        String id = invoice.getId();
        checkIdFormat(id);
        if (containsId(id)) {
            throw new IllegalArgumentException("Invoice with id: "
                    + id
                    + " already exists in database. It can only be updated.");
        }
        invoices.add(invoice);
        addId(id);
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        if (!containsId(id)) {
            throw new IllegalArgumentException(
                    "Provided id: "
                            + id
                            + " not found in Database.");
        }
        for (Invoice invoice : invoices) {
            if (invoice.getId().equals(id)) {
                return invoice;
            }
        }
        throw new IllegalArgumentException(
                "Provided id: "
                        + id
                        + " was found in Database without connected Invoice."
                        + "Invoice with this id was never properly saved "
                        + "or was not properly deleted from database");
    }

    @Override
    public void updateInvoice(Invoice updatedInvoice) {
        checkForNull(updatedInvoice, "updatedInvoice");
        String updatedInvoiceId = updatedInvoice.getId();
        checkIdFormat(updatedInvoiceId);
        if (!containsId(updatedInvoiceId)) {
            throw new IllegalArgumentException(
                    "Provided updatedInvoice does not exist in database. "
                            + "Invoice id: " + updatedInvoiceId + " not found."
            );
        }
        int updateIndex = invoices
                .indexOf(getInvoiceById(updatedInvoiceId));
        invoices.set(updateIndex, updatedInvoice);
    }

    @Override
    public void deleteInvoice(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        invoices.remove(getInvoiceById(id));
        idNumbers.remove(id);
    }

    @Override
    public void addId(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        idNumbers.offer(id);
    }

    @Override
    public List<String> getIdNumbers() {
        return idNumbers;
    }

    @Override
    public String getLastId() {
        return idNumbers.peekLast();
    }

    @Override
    public boolean containsId(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        return idNumbers.contains(id);
    }
}
