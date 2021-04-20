package cz.muni.fi.pa165.fleetmanagement.editors;

import java.beans.PropertyEditorSupport;
import org.joda.time.DateTime;

/**
 * @author formiii
 */
public class DateTimeEditor extends PropertyEditorSupport {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            setValue(null);
        } else {
            DateTime dateTime = DateTime.parse(text);
            setValue(dateTime);
        }
    }

    @Override
    public String getAsText() {
        DateTime dateTime = (DateTime) getValue();
        if (dateTime == null) {
            return null;
        } else {
            return dateTime.toString(DATE_FORMAT);
        }
    }
}
