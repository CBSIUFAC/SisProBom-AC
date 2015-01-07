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

import DAO.UsuarioDAO;
import entity.Usuario;

@ManagedBean(name="UsuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	private UsuarioDAO dao = new UsuarioDAO();
	private List<Usuario> listaUsuario = null;
	private Usuario[] selecionados;
	
	public Usuario getUsuario() {
		if(usuario == null)
			usuario = new Usuario();
		return usuario;
	}
	
	public void setUsuario(Usuario setor) {
		this.usuario = usuario;
	}
	
	public void inserirUsuario() {
		try {
			dao.inserirUsuario(usuario);
			listaUsuario = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		//return "setor";
	}
	
	public String deletarUsuario() {
		dao.deletarUsuario(usuario);
		listaUsuario = null;
		return "usuario";
	}
	
	public void deletarUsuarios(ActionEvent event) {
		if (selecionados.length > 0) {
			for (Usuario setor : selecionados) {
				dao.deletarUsuario(usuario);
			}
		}
	}
	
	public List<Usuario> getListaUsuarios() {
		if(listaUsuario == null)
			listaUsuario = dao.getListaUsuario();
		return listaUsuario;
	}

	public Usuario[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Usuario[] selecionados) {
		this.selecionados = selecionados;
	}

}
