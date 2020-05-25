package pl.futurecollars.invoices.database.company;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.futurecollars.invoices.exceptions.CompanyNotFoundException;
import pl.futurecollars.invoices.model.Company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Primary
public class InMemoryCompanyDatabase implements CompanyDatabase {

    private Map<Long, Company> companies = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();

    @Override
    public long saveCompany(Company company) {
        long id = idCounter.incrementAndGet();
        company.setId(id);
        companies.put(id, company);
        return id;
    }

    @Override
    public List<Company> getCompanies() {
        return new ArrayList<>(companies.values());
    }

    @Override
    public Optional<Company> getCompanyById(long id) {
        return Optional.ofNullable(companies.get(id));
    }

    @Override
    public void updateCompany(long id, Company updatedCompany) {
        updatedCompany.setId(id);
        Company originalCompany = companies.replace(id, updatedCompany);
        if (originalCompany == null) {
            throw new CompanyNotFoundException(id);
        }
    }

    @Override
    public void deleteCompany(long id) {
        Company deletedCompany = companies.remove(id);
        if (deletedCompany == null) {
            throw new CompanyNotFoundException(id);
        }
    }
}
