package pl.futurecollars.invoices.database.company;

import org.springframework.data.repository.CrudRepository;
import pl.futurecollars.invoices.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

}
