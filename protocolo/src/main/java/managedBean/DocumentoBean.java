package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import DAO.DocumentoDAO;
import entity.Documento;

@ManagedBean(name="documentoBean")
@SessionScoped
public class DocumentoBean implements Serializable {
	
	private Documento documento;
	private DocumentoDAO dao = new DocumentoDAO();
	private List<Documento> lista = null;
	private List<Documento> filtro = null;
	private Documento[] selecionados;
	
	public Documento getDocumento() {
		if(documento == null)
			documento = new Documento();
		return documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public void salvarDocumento() {
		try {
			String textoMsg = null;
			if (documento.getProtocolo() == 0) {
				dao.inserirDocumento(documento);
				textoMsg = "Registro incluído com sucesso!";
			} else {
				dao.atualizarDocumento(documento);
				textoMsg = "Registro alterado com sucesso!";
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
				selecionados = null;
			}
			lista = null;
			documento = null;
			mensagem(textoMsg, FacesMessage.SEVERITY_INFO);
		} catch (JDBCException e) {
			mensagem(e.getSQLException().getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deletarDocumentos() {
		if (selecionados.length > 0) {
			try {
				for (Documento documento : selecionados) {
					dao.deletarDocumento(documento);
				}
				lista = null;
				mensagem("Registro(s) removido(s) com sucesso!", FacesMessage.SEVERITY_INFO);
			} catch (JDBCException e) {
				mensagem(e.getSQLException().getMessage(), FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public List<Documento> getLista() {
		if(lista == null)
			lista = dao.getListaDocumento();
		return lista;
	}
	
	public List<Documento> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<Documento> filtro) {
		this.filtro = filtro;
	}

	public Documento[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Documento[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		documento = (Documento) e.getObject();
	}
	
	public void cancelar() {
		documento = null;
	}
	
	public void arquivar() {
		if (selecionados.length > 0) {
			try {
				for (Documento documento : selecionados) {
					documento.setArquivado(true);
					dao.atualizarDocumento(documento);
				}
				lista = null;
				mensagem("Registro atualizado com sucesso!", FacesMessage.SEVERITY_INFO);
			} catch (JDBCException e) {
				mensagem(e.getSQLException().getMessage(), FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public void mensagem(String msg, Severity s) {
		FacesMessage facesMsg = new FacesMessage(s, null, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

}