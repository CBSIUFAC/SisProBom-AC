package managedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.exception.DataException;

import DAO.DocumentoDAO;
import entity.Documento;

@ManagedBean(name="documentoBean")
@SessionScoped
public class DocumentoBean implements Serializable {
	
	private Documento documento;
	private DocumentoDAO dao = new DocumentoDAO();
	private List<Documento> listaDocumento = null;
	private Documento[] selecionados;
	
	public Documento getDocumento() {
		if(documento == null)
			documento = new Documento();
		return documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public void inserirDocumento() {
		try {
			dao.inserirDocumento(documento);
			listaDocumento = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		//return "documento";
	}
	
	public String deletarDocumento() {
		dao.deletarDocumento(documento);
		listaDocumento = null;
		return "documento";
	}
	
	public void deletarDocumentos(ActionEvent event) {
		if (selecionados.length > 0) {
			for (Documento documento : selecionados) {
				dao.deletarDocumento(documento);
			}
		}
	}
	
	public List<Documento> getListaDocumentos() {
		if(listaDocumento == null)
			listaDocumento = dao.getListaDocumento();
		return listaDocumento;
	}

	public Documento[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Documento[] selecionados) {
		this.selecionados = selecionados;
	}

}
