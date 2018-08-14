package edu.mum.cs.cs425.ahacarrentalservice.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;

@FacesConverter(value = "localDateConverter")
public class LocalDateConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            String[] arr = value.split("\\/");
            return LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            StringBuilder stringBuilder = new StringBuilder();
            LocalDate localDate = (LocalDate) value;
            stringBuilder.append(localDate.getMonthValue());
            stringBuilder.append("/");
            stringBuilder.append(localDate.getDayOfMonth());
            stringBuilder.append("/");
            stringBuilder.append(localDate.getYear());
            return stringBuilder.toString();
        }
        return null;
    }
}
