package br.com.authentication.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Conversor de data
 * @author anderson
 */
@SuppressWarnings("WeakerAccess")
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime date) {
        return Optional.ofNullable(date)
                .map(d -> Date.from(d.atZone(ZoneId.systemDefault()).toInstant()))
                .orElse(null);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date value) {
        return Optional.ofNullable(value)
                .map(v ->LocalDateTime.from(v.toInstant().atZone(ZoneId.systemDefault())))
                .orElse(null);
    }
}




   
