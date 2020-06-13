package pl.futurecollars.invoices.service.company;

import static pl.futurecollars.invoices.config.ApiConstants.BAD_REQUEST_MESSAGE;
import static pl.futurecollars.invoices.config.ApiConstants.CONTAINER_LIST;
import static pl.futurecollars.invoices.config.ApiConstants.NOT_FOUND_MESSAGE;
import static pl.futurecollars.invoices.config.ApiConstants.OK_MESSAGE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = {"company-controller"})
public interface CompanyApi {

    @ApiOperation(value = "Get list of all companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = OK_MESSAGE,
                    response = Company.class, responseContainer = CONTAINER_LIST),
    })
    @GetMapping
    List<Company> getCompanies();

    @ApiOperation(value = "Find company by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = OK_MESSAGE,
                    response = CompanyApi.class),
            @ApiResponse(code = 404, message = NOT_FOUND_MESSAGE),
    })
    @GetMapping("/{id}")
    ResponseEntity<?> getCompanyById(@PathVariable("id") @Min(1) long id);

    @ApiOperation(value = "Create a new company", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = OK_MESSAGE, response = Long.class),
            @ApiResponse(code = 400, message = BAD_REQUEST_MESSAGE,
                    response = String.class, responseContainer = CONTAINER_LIST),
    })
    @PostMapping
    ResponseEntity<?> saveCompany(@RequestBody Company company);

    @ApiOperation(value = "Update an existing company")
    @ApiResponses({
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 400, message = BAD_REQUEST_MESSAGE,
                    response = String.class, responseContainer = CONTAINER_LIST),
            @ApiResponse(code = 404, message = NOT_FOUND_MESSAGE),
    })
    @PutMapping("/{id}")
    ResponseEntity<?> updateCompany(@PathVariable("id") @Min(1) long id,
                                    @RequestBody Company updatedCompany);

    @ApiOperation(value = "Delete an existing company")
    @ApiResponses({
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 400, message = BAD_REQUEST_MESSAGE,
                    response = String.class, responseContainer = CONTAINER_LIST),
            @ApiResponse(code = 404, message = NOT_FOUND_MESSAGE),
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCompany(@PathVariable("id") @Min(1) long id);
}
