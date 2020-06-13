package pl.futurecollars.invoices.database.company;

import static pl.futurecollars.invoices.config.DbConstants.APARTMENT_NUMBER;
import static pl.futurecollars.invoices.config.DbConstants.CITY;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_ADDRESS;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_ID;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_NAME;
import static pl.futurecollars.invoices.config.DbConstants.COMPANY_TAX_IDENTIFICATION_NUMBER;
import static pl.futurecollars.invoices.config.DbConstants.POSTAL_CODE;
import static pl.futurecollars.invoices.config.DbConstants.STREET_NAME;
import static pl.futurecollars.invoices.config.DbConstants.STREET_NUMBER;
import static pl.futurecollars.invoices.config.DbConstants.TAX_IDENTIFICATION_NUMBER;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.model.PostalAddress;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MongoDatabase {

    private static MongoClient mongoClient;
    private static DBCollection collection;
    private AtomicLong companyId = new AtomicLong();

    public long saveCompany(Company company) {
        company.setId(companyId.incrementAndGet());
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
        collection.update(query, convertCompanyToDBObject(updatedCompany));
    }

    public DBObject convertCompanyToDBObject(Company company) {
        return new BasicDBObject(COMPANY_ID, companyId)
                .append(COMPANY_NAME, company.getName())
                .append(COMPANY_TAX_IDENTIFICATION_NUMBER, company.getTaxIdentificationNumber())
                .append(COMPANY_ADDRESS, new BasicDBObject(POSTAL_CODE, company.getAddress().getPostalCode())
                        .append(CITY, company.getAddress().getCity())
                        .append(STREET_NAME, company.getAddress().getStreetName())
                        .append(STREET_NUMBER, company.getAddress().getStreetNumber())
                        .append(APARTMENT_NUMBER, company.getAddress().getApartmentNumber()));
    }

    public Company convertDBObjectToCompany(DBObject dbObject) {
        return new Company((String)dbObject.get(TAX_IDENTIFICATION_NUMBER),
                (String)dbObject.get(COMPANY_NAME),
                (PostalAddress)dbObject.get(COMPANY_ADDRESS));
    }

    public static void initMongoDb() throws UnknownHostException {
        mongoClient = new MongoClient();
        collection = mongoClient.getDB("CompanyDatabase")
                .getCollection("Companies");
    }
}
