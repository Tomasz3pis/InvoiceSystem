package pl.futurecollars.invoice.service;

import org.springframework.stereotype.Service;
import pl.futurecollars.invoice.database.InMemoryCompanyDatabase;
import pl.futurecollars.invoice.model.Company;

import java.util.List;

@Service
public class CompanyService {

    private final InMemoryCompanyDatabase database;

    public CompanyService(InMemoryCompanyDatabase database) {
        this.database = database;
    }

    public Company saveCompany(Company company) {
        if (company.getId() != null) {
            throw new IllegalArgumentException("Id should be empty in post request");
        }
        return database.save(company);
    }

    public List<Company> getCompanies() {
        return database.getAll();
    }

    public Company getCompany(Long id) {
        return database.getById(id);
    }

    public void updateCompany(Company updatedCompany) {
        database.update(updatedCompany);
    }

    public void deleteCompany(Long id) {
        database.delete(id);
    }
}
