package pl.futurecollars.invoices.database.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isEmpty()) {
            return Optional.empty();
        }
        return byId;
    }

    public void updateCompany(long id, Company updatedCompany) {
        Company originalCompany = companyRepository.findById(id).get();
        originalCompany.setTaxIdentificationNumber(updatedCompany.getTaxIdentificationNumber());
        originalCompany.setName(updatedCompany.getName());
        originalCompany.setAddress(updatedCompany.getAddress());
        companyRepository.save(originalCompany);
    }

    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }
}
