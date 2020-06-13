package pl.futurecollars.invoices.service.company;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.futurecollars.invoices.providers.TestCompanyProvider.companyBuyMore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.futurecollars.invoices.database.company.CompanyDatabase;
import pl.futurecollars.invoices.model.Company;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyDatabase database;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void getCompanies() {
        //given

        //when
        companyService.getCompanies();

        //then
        verify(database).getCompanies();
    }

    @Test
    void saveCompany() {
        //given
        long id = 1L;
        Company company = companyBuyMore();
        when(database.getCompanyById(id)).thenReturn(Optional.of(company));

        //when
        companyService.saveCompany(company);

        //then
        assertThat(companyService.getCompany(id),
                is(Optional.of(company)));
    }

    @Test
    void updateCompany() {
        //given
        long id = 1L;
        Company company = companyBuyMore();

        //when
        companyService.updateCompany(id, company);

        //then
        verify(database).updateCompany(id, company);
    }

    @Test
    void deleteCompany() {
        //given
        long id = 1L;

        //when
        companyService.deleteCompany(id);

        //then
        verify(database).deleteCompany(id);
    }
}
