package pl.futurecollars.invoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ValidatingService companyValidatingService;

    @Override
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @Override
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
        Optional<Company> companyOptional = companyService.getCompany(id);
        if (companyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companyOptional.get());
    }

    @Override
    public ResponseEntity<Long> saveCompany(@RequestBody Company company) {
        companyValidatingService.validateInput(company);
        long id = companyService.saveCompany(company);
        return ResponseEntity.ok(id);
    }

    @Override
    public  ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company updatedCompany) {
        if (companyService.getCompany(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        companyValidatingService.validateInput(updatedCompany);
        companyService.updateCompany(id, updatedCompany);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteCompany(@PathVariable("id") long id) {
        if (companyService.getCompany(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
