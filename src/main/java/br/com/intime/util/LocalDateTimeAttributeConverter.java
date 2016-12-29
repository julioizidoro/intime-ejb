/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jizid
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter  implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime entityValue) {
        if (entityValue != null) {
            return Time.valueOf(entityValue);
        }
        return null;
    }

    @Override
    public LocalTime convertToEntityAttribute(Time databaseValue) {
        if (databaseValue != null) {
            return databaseValue.toLocalTime();
        }
        return null;
    }
    
    
}
