package pl.futurecollars.invoice.database;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Database<T, T1> {

    T save(T invoice);

    List<T> getAll();

    T getById(T1 id);

    T update(T updatedInvoice);

    void delete(T1 id);
}
