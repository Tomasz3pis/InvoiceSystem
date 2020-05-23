package pl.futurecollars.invoices.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoices.database.Database;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@RestController
@Validated
public class TaxCalculatorController {

    private TaxCalculatorService taxCalculatorService;

    private Database database;

    public TaxCalculatorController(TaxCalculatorService taxCalculatorService,  Database database) {
        this.taxCalculatorService = taxCalculatorService;
        this.database = database;
    }

    @GetMapping("companies/incomevat/{id}")
    public BigDecimal calculateIncomeVat(@PathVariable(name = "id")
                                             @NotBlank(message = "Tax id is mandratory")
                                                     String taxIdentificationNumber) {
        return taxCalculatorService.calculateIncomeVat(database.getInvoices(), taxIdentificationNumber);
    }

    @GetMapping("companies/outcomevat/{id}")
    public BigDecimal calculateOutcomeVat(@PathVariable(name = "id")
                                              @NotBlank(message = "Tax id is mandratory")
                                                      String taxIdentificationNumber) {
        return taxCalculatorService.calculateOutcomeVat(database.getInvoices(), taxIdentificationNumber);
    }

    @GetMapping("companies/income/{id}")
    public BigDecimal calculateIncome(@PathVariable(name = "id")
                                          @NotBlank(message = "Tax id is mandratory")
                                                  String taxIdentificationNumber) {
        return taxCalculatorService.calculateIncome(database.getInvoices(), taxIdentificationNumber);
    }

    @GetMapping("companies/costs/{id}")
    public BigDecimal calculateCosts(@PathVariable(name = "id")
                                         @NotBlank(message = "Tax id is mandratory")
                                                 String taxIdentificationNumber) {
        return taxCalculatorService.calculateCosts(database.getInvoices(), taxIdentificationNumber);
    }

    @GetMapping("companies/incometocosts/{id}")
    public BigDecimal calculateIncomeToCosts(@PathVariable(name = "id")
                                                 @NotBlank(message = "Tax id is mandratory")
                                                         String taxIdentificationNumber) {
        return taxCalculatorService.calculateIncomeToCosts(database.getInvoices(), taxIdentificationNumber);
    }

}
