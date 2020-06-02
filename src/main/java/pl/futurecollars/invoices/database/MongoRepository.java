package pl.futurecollars.invoices.database;

import com.mongodb.DBObject;

import java.util.Optional;

public interface MongoRepository {

    <T> long save(T object);

    void readAll();

    <T> Optional<T> findById(long id);

    void deleteById(long id);

    <T> void update(long id, T object);

    <T> DBObject convertToDBObject(T object);

    <T> T convertFromDBObject(DBObject dbObject);
}
