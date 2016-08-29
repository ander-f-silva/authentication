package br.com.authentication.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Conversor de data
 * @author anderson
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {
    
    @Override
    public Date convertToDatabaseColumn(LocalDateTime date) {
	if (date == null)
	    return null;
	
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date value) {
	if (value == null)
	    return null;
	
        return LocalDateTime.from(value.toInstant().atZone(ZoneId.systemDefault()));
    }
}




   
