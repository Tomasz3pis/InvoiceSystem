package pl.futurecollars.invoices.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    private Company company;

    @InjectMocks
    private CompanyService injectedCompanyService;

    @Test
    void getCompanies() {
        //given

        //when
        injectedCompanyService.getCompanies();

        //then
        verify(database).getCompanies();
    }

    @Test
    void saveCompany() {
        //given
        long id = 1L;
        when(database.getCompanyById(id)).thenReturn(Optional.of(company));

        //when
        injectedCompanyService.saveCompany(company);

        //then
        assertThat(injectedCompanyService.getCompany(id),
                is(Optional.of(company)));
    }

    @Test
    void updateCompany() {
        //given
        long id = 1L;

        //when
        injectedCompanyService.updateCompany(id, company);

        //then
        verify(database).updateCompany(id, company);
    }

    @Test
    void deleteCompany() {
        //given
        long id = 1L;

        //when
        injectedCompanyService.deleteCompany(id);

        //then
        verify(database).deleteCompany(id);
    }
}