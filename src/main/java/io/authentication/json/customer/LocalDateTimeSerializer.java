package io.authentication.json.customer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Esta classe será usada para serializa um data do tipo string para uma data
 * 
 * @author anderson
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    public static final String FORMATE_DATE = "dd/MM/yyyy hh:mm:ss";

    /**
     * Método usado para Serializar o objeto string para data
     */
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
	gen.writeString(value.format(DateTimeFormatter.ofPattern(FORMATE_DATE)));
    }

}
