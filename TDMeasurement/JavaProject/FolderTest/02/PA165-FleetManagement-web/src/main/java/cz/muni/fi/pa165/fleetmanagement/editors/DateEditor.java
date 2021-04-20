package cz.muni.fi.pa165.fleetmanagement.editors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ján Švec
 */
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            setValue(null);
        } else {
            try {
                setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
            } catch (ParseException e) {
                setValue(null);
            }
        }
    }

    @Override
    public String getAsText() {
        Date date = (Date) getValue();
        if (date == null) {
            return null;
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
        }
    }
//    @Override
//    public void setAsText(String value) {
//      try {
//        setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
//      } catch(ParseException e) {
//        setValue(null);
//      }
//    }
//
//    @Override
//    public String getAsText() {
//      String s = "";
//      if (getValue() != null) {
//         s = new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
//      }
//      return s;
//    }
}
