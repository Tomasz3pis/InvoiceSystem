package pl.futurecollars.invoices.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.futurecollars.invoices.model.Invoice;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RequestMapping("/invoices")
@Validated
@Api(value = "Invoices", description = "Controller used for basic operations on invoice objects")
public interface InvoiceApi {

    String DATE_REGEX = "^(19(\\d){2}|2(\\d){3})-(([0][1-9])|([1][0-2]))-(([0][1-9])|([1-2]\\d)|([3][0-1]))$";

    @GetMapping
    @ApiOperation(
            value = "Get list of all invoices (optionally: get list of invoices by date range)",
            response = Invoice.class,
            responseContainer = "List",
            produces = "application/json")
    List<Invoice> getInvoices(@RequestParam(name = "startDate", required = false)
                              @ApiParam(value = "start date (inclusive)")
                              @Pattern(regexp = (DATE_REGEX),
                                      message = "startDate: must match YYYY-MM-DD pattern")
                                      String startDate,
                              @RequestParam(name = "endDate", required = false)
                              @ApiParam(value = "end date (inclusive)")
                              @Pattern(regexp = (DATE_REGEX),
                                      message = "endDate: must match YYYY-MM-DD pattern")
                                      String endDate);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an invoice by id", response = Invoice.class, produces = "application/json")
    Invoice getInvoiceById(@PathVariable("id")

                           @ApiParam(value = "id of an existing invoice to get")
                           @Min(1) long id);

    @PostMapping
    @ApiOperation(value = "Save an invoice to database", response = long.class)
    long saveInvoice(@RequestBody @ApiParam(value = "body of an invoice to save") Invoice invoice);

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an invoice at given id", response = Void.class)
    void updateInvoice(@PathVariable("id")
                       @Min(1)
                       @ApiParam(value = "id of an existing invoice to update")
                               long id,
                       @RequestBody
                       @ApiParam(value = "body of an invoice with updated fields")
                               Invoice updatedInvoice);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an invoice at given id", response = Void.class)
    void deleteInvoice(@PathVariable("id") @Min(1) @ApiParam(value = "id of an existing invoice to delete") long id);
}
