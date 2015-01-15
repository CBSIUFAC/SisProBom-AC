package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import entity.Funcionario;

@FacesConverter("funcionarioConverter")
public class funcionarioConverter extends SelectItemsConverter {
	public String getAsObject(FacesContext context, UIComponent component, Object value) {
        Integer matricula = (value instanceof Funcionario) ? ((Funcionario) value).getMatricula() : null;
        return (matricula != null) ? String.valueOf(matricula) : null;
    }
}
