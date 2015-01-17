package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;



import entity.Funcionario;
import entity.Setor;

@FacesConverter("funcionarioConverter")
public class funcionarioConverter extends SelectItemsConverter {
	public String getAsObject(FacesContext context, UIComponent component, Object value) {
        Integer id_funcionario = (value instanceof Funcionario) ? ((Funcionario) value).getId_funcionario()() : null;
        return (id_funcionario != null) ? String.valueOf(id_funcionario) : null;
    }
}
