package pl.futurecollars.invoices.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JsonParserHelperTest {

    @InjectMocks
    private JsonParserHelper jsonParserHelper;

    @Test
    public void testObjectToJson() {
        // given
        TestClass testClass = new TestClass();
        testClass.setId(12L);
        testClass.setName("test");
        // when
        String json = jsonParserHelper.objectToJson(testClass);
        // then
        assertThat(json).isEqualTo("{\"id\":12,\"name\":\"test\"}");
    }

    @Test
    public void testJsonToObject() throws JsonProcessingException {
        // given
        String json = "{\"id\":65000}";
        // when
        Invoice result = (Invoice) jsonParserHelper.jsonToObject(json, Invoice.class);
        // then
        assertThat(result.getId()).isEqualTo(65000L);
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

        public TestClass(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public TestClass() { }
    }
}
