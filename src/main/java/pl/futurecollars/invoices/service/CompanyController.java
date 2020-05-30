package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.exceptions.CompanyNotFoundException;
import pl.futurecollars.invoices.model.Company;

import java.util.List;

@RestController
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ValidatingService validatingService;

    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
        if (companyService.getCompany(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Company company = companyService.getCompany(id).get();
        return ResponseEntity.ok(company);
    }

    public long saveCompany(@RequestBody Company company) {
        validatingService.validateInput(company);
        return companyService.saveCompany(company);
    }

    public void updateCompany(@PathVariable("id") long id, @RequestBody Company updatedCompany) {
        validatingService.validateInput(updatedCompany);
        companyService.updateCompany(id, updatedCompany);
    }

    public void deleteCompany(@PathVariable("id") long id) {
        if (companyService.getCompany(id).isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        companyService.deleteCompany(id);
    }
}
