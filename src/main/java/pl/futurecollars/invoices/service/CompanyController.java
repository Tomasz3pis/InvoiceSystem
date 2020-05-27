package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.database.company.CompanyRepository;
import pl.futurecollars.invoices.exceptions.CompanyNotFoundException;
import pl.futurecollars.invoices.model.Company;

import java.util.List;

@RestController
@RequestMapping("/companies")
@Validated
public class CompanyController {

    @Autowired
    private CompanyRepository database;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ValidatingService validatingService;

    @GetMapping()
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable("id") long id) {
        if (companyService.getCompany(id).isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        return companyService.getCompany(id).get();
    }

    @PostMapping()
    public long saveCompany(@RequestBody Company company) {
        validatingService.validateInput(company);
        return companyService.saveCompany(company);
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@PathVariable("id") long id, @RequestBody Company updatedCompany) {
        validatingService.validateInput(updatedCompany);
        companyService.updateCompany(id, updatedCompany);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable("id") long id) {
        if (companyService.getCompany(id).isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        companyService.deleteCompany(id);
    }
}
