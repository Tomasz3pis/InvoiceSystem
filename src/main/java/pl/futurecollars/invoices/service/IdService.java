package pl.futurecollars.invoices.service;

import static pl.futurecollars.invoices.helpers.CheckForNull.checkForNull;
import static pl.futurecollars.invoices.helpers.CheckIdFormat.checkIdFormat;

import pl.futurecollars.invoices.database.Database;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IdService {

    private Database database;
    private AtomicInteger idCounter;
    private static final int COUNTER_START_INDEX = 9;
    private static final String ID_SEPARATOR = "_";

    public IdService(Database database) {
        checkForNull(database, "database");
        this.database = database;
        if (database.getLastId() == null) {
            this.idCounter = new AtomicInteger(1);
        } else {
            String lastId = database.getLastId();
            this.idCounter = new AtomicInteger(Integer.parseInt(
                    lastId.substring(COUNTER_START_INDEX)) + 1);
        }
    }

    public List<String> getIdNumbers() {
        return database.getIdNumbers();
    }

    public String getNewId(LocalDate saleDate) {
        checkForNull(saleDate, "saleDate");
        return generateId(saleDate);
    }

    private String generateId(LocalDate saleDate) {
        checkForNull(saleDate, "saleDate");
        return String.format("%04d%02d%02d%1s%04d",
                saleDate.getYear(),
                saleDate.getMonthValue(),
                saleDate.getDayOfMonth(),
                ID_SEPARATOR,
                idCounter.getAndIncrement());
    }

    public boolean isIdPresent(String id) {
        checkForNull(id, "id");
        checkIdFormat(id);
        return database.containsId(id);
    }
}
