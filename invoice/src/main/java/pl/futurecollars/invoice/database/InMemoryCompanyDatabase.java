package pl.futurecollars.invoice.database;

import org.springframework.stereotype.Repository;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.service.IdGenerator;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCompanyDatabase implements Database<Company, Long> {
    private List<Company> companies = new ArrayList<>();

    private final IdGenerator idGenerator;

    public InMemoryCompanyDatabase(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Company save(Company company) {
        company.setId(idGenerator.getId());
        if (companies.add(company)) {
            return company;
        }
        return null;
    }

    @Override
    public List<Company> getAll() {
        return companies;
    }

    @Override
    public Company getById(Long id) {
        return companies.stream()
                .filter(Company -> Company.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provide id" + id + "is not found"));
    }

    @Override
    public Company update(Company updatedCompany) {
        int updateIndex = companies.indexOf(getById(updatedCompany.getId()));
        return companies.set(updateIndex, updatedCompany);
    }

    @Override
    public void delete(Long id) {
        companies.removeIf(Company -> Company.getId().equals(id));
        throw new IllegalArgumentException("Provide id" + id + "is not found");
    }
}
