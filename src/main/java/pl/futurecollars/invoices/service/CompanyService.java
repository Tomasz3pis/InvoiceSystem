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
    private CompanyDatabase companyDatabase;

    public List<Company> getCompanies() {
        return companyDatabase.getCompanies();
    }

    public Optional<Company> getCompany(long id) {
        return companyDatabase.getCompanyById(id);
    }

    public long saveCompany(@Valid Company company) {
        return companyDatabase.saveCompany(company);
    }

    public void updateCompany(long id, @Valid Company updatedCompany) {
        companyDatabase.updateCompany(id, updatedCompany);
    }

    public void deleteCompany(long id) {
        companyDatabase.deleteCompany(id);
    }
}
