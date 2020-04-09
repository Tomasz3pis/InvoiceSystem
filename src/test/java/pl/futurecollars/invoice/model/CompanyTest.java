package pl.futurecollars.invoice.model;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class CompanyTest {

    @Test
    void shouldCheckIfToStringIsWorking() {
        //given

        //when

        //then
        ToStringVerifier.forClass(Company.class)
                .verify();
    }

    @Test
    void shouldCheckIfEqualsAndHashcodeIsWorking() {
        //given

        //when

        //then
        EqualsVerifier.forClass(Company.class)
                .verify();
    }
}
