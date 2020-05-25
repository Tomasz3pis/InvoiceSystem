package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.database.company.CompanyDatabase;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Service
public class CompanyService {

    @Autowired
    private CompanyDatabase database;

    public List<Company> getCompanies() {
        return database.getCompanies();
    }

    public Optional<Company> getCompany(long id) {
        return database.getCompanyById(id);
    }

    public long saveCompany(@Valid Company company) {
        return database.saveCompany(company);
    }

    public void updateCompany(long id, @Valid Company updatedCompany) {
        database.updateCompany(id, updatedCompany);
    }

    public void deleteCompany(long id) {
        database.deleteCompany(id);
    }
}
