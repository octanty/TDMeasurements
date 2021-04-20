package cz.muni.fi.pa165.bottler.web;

import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public class JodaDateTimeConverter implements TypeConverter<DateTime> {

    @Override
    public void setLocale(Locale locale) {
    }

    @Override
    public DateTime convert(String string, Class<? extends DateTime> type, Collection<ValidationError> clctn) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime datetime = formatter.parseDateTime(string);
        return datetime;
    }
}
