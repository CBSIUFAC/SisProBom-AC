package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
	
	public void salvarSetor() {
		try {
			String textoMsg = null;
			if (setor.getId() == 0) {
				dao.inserirSetor(setor);
				textoMsg = "Registro incluído com sucesso!";
			} else {
				dao.atualizarSetor(setor);
				textoMsg = "Registro alterado com sucesso!";
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
				selecionados = null;
			}
			listaSetor = null;
			setor = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void deletarSetores() {
		if (selecionados.length > 0) {
			try {
				for (Setor setor : selecionados) {
					dao.deletarSetor(setor);
				}
				listaSetor = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro removido com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (JDBCException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
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
	
	public void preparaEdicao(SelectEvent e) {
		setor = (Setor) e.getObject();
	}
	
	public void cancelar() {
		setor = null;
	}

}
