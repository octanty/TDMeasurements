/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author Rex
 */
public class DateConverter implements TypeConverter<LocalDate> {

    @Override
    public void setLocale(Locale locale) {
        
    }

    @Override
    public LocalDate convert(String string, Class<? extends LocalDate> type, Collection<ValidationError> clctn) {
        try {
            return LocalDate.parse(string, DateTimeFormat.forPattern("dd.MM.yyyy"));
        } catch(IllegalArgumentException e) {
            clctn.add(new SimpleError("Bad date format"));
            return null;
        }
    }    
}
