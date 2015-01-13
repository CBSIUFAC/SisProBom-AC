package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.JDBCException;

import DAO.SetorDAO;
import entity.Setor;

@ManagedBean(name="setorBean")
@SessionScoped
public class SetorBean implements Serializable {
	
	private Setor setor;
	private SetorDAO dao = new SetorDAO();
	private List<Setor> listaSetor = null;
	private Setor[] selecionados;
	
	public Setor getSetor() {
		if(setor == null)
			setor = new Setor();
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public void inserirSetor() {
		try {
			dao.inserirSetor(setor);
			listaSetor = null;
			setor = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public String deletarSetor() {
		dao.deletarSetor(setor);
		listaSetor = null;
		return "setor";
	}
	
	public void deletarSetores() {
		if (selecionados.length > 0) {
			for (Setor setor : selecionados) {
				dao.deletarSetor(setor);
			}
			listaSetor = null;
		}
	}
	
	public List<Setor> getListaSetores() {
		if(listaSetor == null)
			listaSetor = dao.getListaSetor();
		return listaSetor;
	}

	public Setor[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Setor[] selecionados) {
		this.selecionados = selecionados;
	}

}
