/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.LiquorDto;
import cz.muni.fi.pa165.bottler.service.LiquorBottleService;
import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Converts liquor ID to liquor dto
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Controller
public class LiquorIdToLiquorConverter implements TypeConverter<LiquorDto>{

    @SpringBean
    protected LiquorBottleService liquorBottleService;
    
    
    @Override
    public void setLocale(Locale locale) {
        
    }

    @Override
    public LiquorDto convert(String string, Class<? extends LiquorDto> type, Collection<ValidationError> validationErrors) {
        
        long id = Long.parseLong(string);
        System.out.println("id=" + string);
        
        LiquorDto converted = liquorBottleService.findLiquorById(id);
        if(converted == null){
            validationErrors.add(new SimpleError("Stamp couldn't have been parsed.", ""));
            return null;            
        }
        
        return converted;
    }

}
