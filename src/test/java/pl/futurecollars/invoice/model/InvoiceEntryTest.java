package pl.futurecollars.invoice.model;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class InvoiceEntryTest {

    @Test
    void shouldCheckIfToStringIsWorking() {
        //given

        //when

        //then
        ToStringVerifier.forClass(InvoiceEntry.class)
                .verify();
    }

    @Test
    void shouldCheckIfEqualsAndHashcodeIsWorking() {
        //given

        //when

        //then
        EqualsVerifier.forClass(InvoiceEntry.class)
                .verify();
    }
}
