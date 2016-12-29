/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.util;

import java.time.LocalDate;
import java.sql.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jizid
 */
@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements  AttributeConverter<LocalDate, Date>{
    
   

    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        if (entityValue != null) {
            return Date.valueOf(entityValue);
        }
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        if (databaseValue != null) {
            return databaseValue.toLocalDate();
        }
        return null;
    }
}
