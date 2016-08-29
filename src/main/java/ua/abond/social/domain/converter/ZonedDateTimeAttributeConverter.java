package ua.abond.social.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter
public class ZonedDateTimeAttributeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return (
                zonedDateTime == null ?
                        null :
                        new Timestamp(zonedDateTime.toInstant().getEpochSecond() * 1000L)
        );
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        return ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
    }
}
