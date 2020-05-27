package pl.futurecollars.invoices.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonParserHelperTest {

    @InjectMocks
    private JsonParserHelper jsonParserHelper;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void testObjectToJson() throws JsonProcessingException {
        // given
        TestClass testClass = new TestClass();
        testClass.setId(12L);
        testClass.setName("test");
        String expectedJson = "{\"id\":12,\"name\":\"test\"}";
        when(objectMapper.writeValueAsString(testClass)).thenReturn(expectedJson);
        // when
        String json = jsonParserHelper.objectToJson(testClass);
        // then
        assertThat(json).isEqualTo(expectedJson);
    }

    @Test
    public void testJsonToInvoice() throws JsonProcessingException {
        // given
        String json = "{\"id\":65000}";
        Invoice expectedResult = new Invoice();
        when(objectMapper.readValue(json, Invoice.class)).thenReturn(expectedResult);
        // when
        Invoice result = jsonParserHelper.jsonToInvoice(json);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private class TestClass {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

         TestClass(Long id, String name) {
            this.id = id;
            this.name = name;
        }

      TestClass() { }
    }
}
