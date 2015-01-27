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

import DAO.OrgaoExternoDAO;
import entity.OrgaoExterno;


@ManagedBean(name="orgaoExternoBean")
@SessionScoped
public class OrgaoExternoBean implements Serializable {
	
	private OrgaoExterno orgao;
	private OrgaoExternoDAO dao = new OrgaoExternoDAO();
	private List<OrgaoExterno> lista = null;
	private List<OrgaoExterno> filtro = null;
	private OrgaoExterno[] selecionados;
	private int tipoSetor;
	
	public int getTipoSetor() {
		return tipoSetor;
	}

	public void setTipoSetor(int tipoSetor) {
		this.tipoSetor = tipoSetor;
	}

	public OrgaoExterno getOrgao() {
		if(orgao == null)
			orgao = new OrgaoExterno();
		return orgao;
	}
	
	public void setOrgao(OrgaoExterno orgao) {
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
			orgao = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void deletarOrgao() {
		if (selecionados.length > 0) {
			try {
				for (OrgaoExterno orgao : selecionados) {
					dao.deletarOrgao(orgao);
				}
				lista = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro removido com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (JDBCException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public List<OrgaoExterno> getLista() {
		if(lista == null)
			lista = dao.getListaOrgao();
		return lista;
	}
	
	public List<OrgaoExterno> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<OrgaoExterno> filtro) {
		this.filtro = filtro;
	}

	public OrgaoExterno[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(OrgaoExterno[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		orgao = (OrgaoExterno) e.getObject();
	}
	
	public void cancelar() {
		orgao = null;
	}

}
