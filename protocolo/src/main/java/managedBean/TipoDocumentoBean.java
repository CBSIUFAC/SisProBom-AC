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

import DAO.TipoDocumentoDAO;
import entity.TipoDocumento;

@ManagedBean(name="tipoDocumentoBean")
@SessionScoped
public class TipoDocumentoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9188790501996395628L;
	
	private TipoDocumento tipoDocumento;
	private TipoDocumentoDAO dao = new TipoDocumentoDAO();
	private List<TipoDocumento> lista = null;
	private List<TipoDocumento> filtro = null;
	private TipoDocumento[] selecionados;
	
	public TipoDocumento getTipoDocumento() {
		if(tipoDocumento == null)
			tipoDocumento = new TipoDocumento();
		return tipoDocumento;
	}
	
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public void salvarTipoDocumento() {
		try {
			String textoMsg = null;
			if (tipoDocumento.getId() == null) {
				dao.inserirTipoDocumento(tipoDocumento);
				textoMsg = "Registro incluído com sucesso!";
			} else {
				dao.atualizarTipoDocumento(tipoDocumento);
				textoMsg = "Registro alterado com sucesso!";
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
				selecionados = null;
			}
			lista = null;
			tipoDocumento = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void deletarTipoDocumentos() {
		if (selecionados.length > 0) {
			try {
				for (TipoDocumento tipoDocumento : selecionados) {
					dao.deletarTipoDocumento(tipoDocumento);
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
	
	public List<TipoDocumento> getLista() {
		if(lista == null)
			lista = dao.getListaTipoDocumento();
		return lista;
	}
	
	public List<TipoDocumento> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<TipoDocumento> filtro) {
		this.filtro = filtro;
	}

	public TipoDocumento[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(TipoDocumento[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		tipoDocumento = (TipoDocumento) e.getObject();
	}
	
	public void cancelar() {
		tipoDocumento = null;
	}

}
