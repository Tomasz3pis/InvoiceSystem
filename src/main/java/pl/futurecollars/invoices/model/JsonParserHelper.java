package pl.futurecollars.invoices.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonParserHelper<T> {

    final Logger logger = LoggerFactory.getLogger(JsonParserHelper.class);

    public String objectToJson(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(o);
        } catch (
                JsonProcessingException e) {
            logger.error("Wrong input format");
        }
        return null;
    }

    public T jsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
