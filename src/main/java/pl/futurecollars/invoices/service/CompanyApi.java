package pl.futurecollars.invoices.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.futurecollars.invoices.model.Company;

import java.util.List;
import javax.validation.constraints.Min;

@RequestMapping("/companies")
@Validated
@Api(value = "Companies", description = "Controller used for basic operations on company objects")
public interface CompanyApi {

    @GetMapping
    @ApiOperation(
            value = "Get list of all companies",
            response = Company.class,
            responseContainer = "List",
            produces = "application/json")
    List<Company> getCompanies();

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a company by id", response = ResponseEntity.class, produces = "application/json")
    ResponseEntity<Company> getCompanyById(@PathVariable("id")

                           @ApiParam(value = "id of an existing company to get")
                           @Min(1) long id);

    @PostMapping
    @ApiOperation(value = "Save a company to database", response = Long.class)
    long saveCompany(@RequestBody @ApiParam(value = "body of a company to save") Company company);

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a company at given id", response = Void.class)
    void updateCompany(@PathVariable("id")
                       @Min(1)
                       @ApiParam(value = "id of an existing company to update")
                               long id,
                       @RequestBody
                       @ApiParam(value = "body of a company with updated fields")
                               Company updatedCompany);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a company at given id", response = Void.class)
    void deleteCompany(@PathVariable("id") @Min(1) @ApiParam(value = "id of an existing company to delete") long id);
}
