/*
package edu.mum.cs.cs425.ahacarrentalservice.converter;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter
public class CarModelConverter implements Converter<CarModel> {
    @Override
    public CarModel getAsObject(FacesContext context, UIComponent component, String carModelString) {
        String[] arr = carModelString.split("\\|");
        CarModel carModel = new CarModel();
        carModel.setId(Long.parseLong(arr[0]));
        carModel.setName(arr[1]);
        carModel.setBrand(arr[2]);
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, CarModel value) {
        return null;
    }
}
*/
