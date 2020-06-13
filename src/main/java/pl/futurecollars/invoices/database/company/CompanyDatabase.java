package pl.futurecollars.invoices.database.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Primary
public class CompanyDatabase {

    @Autowired
    private CompanyRepository companyRepository;

    public long saveCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return savedCompany.getId();
    }

    public List<Company> getCompanies() {
        return StreamSupport
                .stream(companyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Company> getCompanyById(long id) {
        return companyRepository.findById(id);
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
