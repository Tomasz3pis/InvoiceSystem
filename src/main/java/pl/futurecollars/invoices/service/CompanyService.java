package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.futurecollars.invoices.database.company.CompanySpringDataDatabase;
import pl.futurecollars.invoices.exceptions.CompanyNotFoundException;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Service
public class CompanyService {

    @Autowired
    private CompanySpringDataDatabase companySpringDataDatabase;

    public List<Company> getCompanies() {
        return companySpringDataDatabase.getCompanies();
    }

    public Optional<Company> getCompany(long id) {
        if (companySpringDataDatabase.getCompanyById(id).isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        return companySpringDataDatabase.getCompanyById(id);
    }

    public long saveCompany(@Valid Company company) {
        return companySpringDataDatabase.saveCompany(company);
    }

    public void updateCompany(long id, @Valid Company updatedCompany) {
        companySpringDataDatabase.updateCompany(id, updatedCompany);
    }

    public void deleteCompany(long id) {
        if (companySpringDataDatabase.getCompanyById(id).isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        companySpringDataDatabase.deleteCompany(id);
    }
}
