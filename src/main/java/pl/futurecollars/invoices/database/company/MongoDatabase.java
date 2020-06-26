package pl.futurecollars.invoices.database.company;

import static pl.futurecollars.invoices.config.DbConstants.APARTMENT_NUMBER;
import static pl.futurecollars.invoices.config.DbConstants.CITY;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_ADDRESS;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_ID;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_NAME;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_TAX_IDENTIFICATION_NUMBER;
import static pl.futurecollars.invoices.config.DbConstants.LAST_USED_ID;
import static pl.futurecollars.invoices.config.DbConstants.MONGO_DB_COLLECTION_NAME;
import static pl.futurecollars.invoices.config.DbConstants.MONGO_DB_NAME;
import static pl.futurecollars.invoices.config.DbConstants.POSTAL_CODE;
import static pl.futurecollars.invoices.config.DbConstants.STREET_NAME;
import static pl.futurecollars.invoices.config.DbConstants.STREET_NUMBER;
import static pl.futurecollars.invoices.config.DbConstants.TAX_IDENTIFICATION_NUMBER;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.PostalAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;

@Repository
@Primary
public class MongoDatabase implements Database {

    private static MongoClient mongoClient;
    private static DBCollection collection;
    private AtomicLong companyId = getLastUsedId();

    public long saveCompany(Company company) {
        company.setId(companyId.incrementAndGet());
        saveLastId(companyId);
        collection.insert(convertCompanyToDBObject(company));
        return company.getId();
    }

    public List<Company> getCompanies() {
        DBCursor dbObjects = collection.find();
        List<Company> companies = new ArrayList<>();
        while (dbObjects.hasNext()) {
            companies.add(convertDBObjectToCompany(dbObjects.next()));
        }
        return companies;
    }

    public Optional<Company> getCompanyById(long id) {
        BasicDBObject query = new BasicDBObject(COMPANY_ID, id);
        DBCursor dbObjects = collection.find(query);
        Company company = null;
        while (dbObjects.hasNext()) {
            company = convertDBObjectToCompany(dbObjects.next());
        }
        return Optional.ofNullable(company);
    }

    public void deleteCompany(long id) {
        BasicDBObject query = new BasicDBObject(COMPANY_ID, id);
        collection.remove(query);
    }

    public void updateCompany(long id, Company updatedCompany) {
        BasicDBObject query = new BasicDBObject(COMPANY_ID, id);
        updatedCompany.setId(id);
        collection.update(query, convertCompanyToDBObject(updatedCompany));
    }

    public DBObject convertCompanyToDBObject(Company company) {
        return new BasicDBObject(COMPANY_ID, company.getId())
                .append(COMPANY_NAME, company.getName())
                .append(COMPANY_TAX_IDENTIFICATION_NUMBER, company.getTaxIdentificationNumber())
                .append(COMPANY_ADDRESS, new BasicDBObject(STREET_NAME, company.getAddress().getStreetName())
                        .append(STREET_NUMBER, company.getAddress().getStreetNumber())
                        .append(APARTMENT_NUMBER, company.getAddress().getApartmentNumber())
                        .append(POSTAL_CODE, company.getAddress().getPostalCode())
                        .append(CITY, company.getAddress().getCity()));
    }

    public Company convertDBObjectToCompany(DBObject dbObject) {
        DBObject dbObjectPostalAddress = (DBObject) dbObject.get(COMPANY_ADDRESS);
        Company company = new Company((String) dbObject.get(TAX_IDENTIFICATION_NUMBER),
                (String) dbObject.get(COMPANY_NAME),
                new PostalAddress((String) dbObjectPostalAddress.get(STREET_NAME),
                        (String) dbObjectPostalAddress.get(STREET_NUMBER),
                        (String) dbObjectPostalAddress.get(APARTMENT_NUMBER),
                        (String) dbObjectPostalAddress.get(POSTAL_CODE),
                        (String) dbObjectPostalAddress.get(CITY)));
        company.setId((long) dbObject.get(COMPANY_ID));
        return company;
    }

    private AtomicLong getLastUsedId() {
        if (companyId == null) {
            return new AtomicLong();
        }
        BasicDBObject query = new BasicDBObject(LAST_USED_ID, companyId);
        DBCursor dbObjects = collection.find(query);
        AtomicLong id = null;
        while (dbObjects.hasNext()) {
            id = (AtomicLong) dbObjects.next();
        }
        return id;
    }

    private void saveLastId(AtomicLong companyId) {
        collection.insert(new BasicDBObject(LAST_USED_ID, companyId));
    }

    @PostConstruct
    private void initMongoDb() {
        mongoClient = new MongoClient();
        collection = mongoClient.getDB(MONGO_DB_NAME)
                .getCollection(MONGO_DB_COLLECTION_NAME);
        collection.drop();
    }
}
