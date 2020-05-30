package pl.futurecollars.invoices.database.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.exceptions.CompanyNotFoundException;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDatabase {

    @Autowired
    private CompanyRepository companyRepository;

    public long saveCompany(Company company) {
        companyRepository.save(company);
        return company.getId();
    }

    public List<Company> getCompanies() {
        return (List<Company>) companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(long id) {
        if (companyRepository.findById(id).isEmpty()) {
            return Optional.empty();
        }
        return companyRepository.findById(id);
    }

    public void updateCompany(long id, Company updatedCompany) {
        companyRepository.findById(id);
        updatedCompany.setId(id);
        companyRepository.save(updatedCompany);
    }

    public void deleteCompany(long id) {
        if (getCompanyById(id).isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        companyRepository.deleteById(id);
    }
}
