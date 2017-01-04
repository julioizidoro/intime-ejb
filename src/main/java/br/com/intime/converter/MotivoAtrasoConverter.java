package br.com.intime.converter;
 
import br.com.intime.model.Empresa;
import br.com.intime.model.Motivoatraso;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kamila
 */
@FacesConverter(value="MotivoAtrasoConverter")
public class MotivoAtrasoConverter implements Converter {
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		List<Motivoatraso> listaMotivoAtraso = (List<Motivoatraso>) arg1.getAttributes().get("listaMotivoAtraso");
	    if (listaMotivoAtraso != null) {
	        for (Motivoatraso motivoatraso : listaMotivoAtraso) {
	            if (motivoatraso.getDescricao().equalsIgnoreCase(arg2)) {
	                return motivoatraso;
	            }
	        }
	    } else {
	        Motivoatraso motivoatraso = new Motivoatraso();
	        return motivoatraso;
	    }
	    Motivoatraso motivoatraso = new Motivoatraso();
	    return motivoatraso;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	        Motivoatraso motivoatraso = (Motivoatraso) arg2;
	        return motivoatraso.getDescricao();
	    }
	}
}
