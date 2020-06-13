package pl.futurecollars.invoices.database.company;

import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.Optional;

public interface Database {

    List<Company> getCompanies();

    Optional<Company> getCompanyById(long id);

    long saveCompany(Company company);

    void updateCompany(long id, Company updatedCompany);

    void deleteCompany(long id);
}
