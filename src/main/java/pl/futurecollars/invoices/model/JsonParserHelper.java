package pl.futurecollars.invoices.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonParserHelper {

    private final Logger logger = LoggerFactory.getLogger(JsonParserHelper.class);

    @Autowired
    private ObjectMapper mapper;

    public String objectToJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Invoice jsonToInvoice(String json) {
        try {
            return mapper.readValue(json, Invoice.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
