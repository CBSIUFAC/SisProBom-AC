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

import DAO.OrgaoDAO;
import entity.Orgao;

@ManagedBean(name="orgaoBean")
@SessionScoped
public class OrgaoBean implements Serializable {
	
	private Orgao orgao;
	private OrgaoDAO dao = new OrgaoDAO();
	private List<Orgao> lista = null;
	private List<Orgao> listaInterno = null;
	private List<Orgao> listaExterno = null;
	private List<Orgao> filtro = null;
	private Orgao[] selecionados;
	
	public Orgao getOrgao() {
		if(orgao == null)
			orgao = new Orgao();
		return orgao;
	}
	
	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}
	
	public void salvarOrgao() {
		try {
			String textoMsg = null;
			if (orgao.getId() == null) {
				dao.inserirOrgao(orgao);
				textoMsg = "Registro incluído com sucesso!";
			} else {
				dao.atualizarOrgao(orgao);
				textoMsg = "Registro alterado com sucesso!";
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
				selecionados = null;
			}
			lista = null;
			listaInterno = null;
			listaExterno = null;
			orgao = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void salvarOrgaoInterno() {
		orgao.setInterno(true);
		salvarOrgao();
	}
	
	public void salvarOrgaoExterno() {
		orgao.setInterno(false);
		salvarOrgao();
	}
	
	public void deletarOrgaos() {
		if (selecionados.length > 0) {
			try {
				for (Orgao orgao : selecionados) {
					dao.deletarOrgao(orgao);
				}
				lista = null;
				listaInterno = null;
				listaExterno = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro removido com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (JDBCException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public List<Orgao> getLista() {
		if(lista == null)
			lista = dao.getListaOrgao();
		return lista;
	}
	
	public List<Orgao> getListaInterno() {
		if (listaInterno == null)
			listaInterno = dao.getListaOrgaoInterno();
		return listaInterno;
	}
	
	public List<Orgao> getListaExterno() {
		if (listaExterno == null)
			listaExterno = dao.getListaOrgaoExterno();
		return listaExterno;
	}
	
	public List<Orgao> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<Orgao> filtro) {
		this.filtro = filtro;
	}

	public Orgao[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Orgao[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		orgao = (Orgao) e.getObject();
	}
	
	public void cancelar() {
		orgao = null;
	}

}
