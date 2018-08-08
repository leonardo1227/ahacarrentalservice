/*
package edu.mum.cs.cs425.ahacarrentalservice.converter;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarBrand;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("carBrandConverter")
public class CarBrandConverter implements Converter<CarBrand> {

    @Override
    public CarBrand getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length()>0){
            String[] arr = value.split("\\|");
            CarBrand carBrand = new CarBrand();
            carBrand.setId(Long.parseLong(arr[0]));
            carBrand

            return carBrand;
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, CarBrand carBrand) {
        if(carBrand!=null && carBrand.getId()!=null){
            return carBrand.getId().toString();
        }
        return null;
    }

    */
/*public static final String separador = "|";

	public Object getAsObject(FacesContext fc, UIComponent uic, String objString)
			throws ConverterException {

		String[] arr = objString.split("\\" + SetorConverter.separador);

//		VwSetores setor = new VwSetores();
		Setor setor = new Setor();
		setor.setId(Integer.valueOf(arr[0]));
		setor.setSigla(arr[1]);
		setor.setDescricao(arr[2]);
		return setor;

	}

	public String getAsString(FacesContext fc, UIComponent uic, Object value)
			throws ConverterException {
		if (value == null) {
			return null;
		}

//		if (value instanceof VwSetores) {
		if (value instanceof Setor) {
//			VwSetores setor = (VwSetores) value;
			Setor setor = (Setor) value;
			return setor.getId() + SetorConverter.separador
					+ setor.getSigla() + SetorConverter.separador + setor.getDescricao();
		} else if (value instanceof String
				&& ((String) value).equalsIgnoreCase("0")) {
			return "0";
		} else {
			throw new ConverterException("A classe " + value.getClass()
					+ " nÃ£o Ã© uma instÃ¢ncia.");
		}
	}
}
*/
