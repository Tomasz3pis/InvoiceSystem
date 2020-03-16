package pl.futurecollars.invoice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoice.model.Company;
import pl.futurecollars.invoice.service.CompanyService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAll() {
        return companyService.getCompanies();
    }

    @PostMapping()
    public Company addCompany(@RequestBody @Valid Company company) {
        return companyService.saveCompany(company);
    }
}
