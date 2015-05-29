package managedBean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
<<<<<<< HEAD
=======
import java.sql.SQLException;
>>>>>>> branch 'master' of https://github.com/CBSIUFAC/SisProBom-AC.git
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
<<<<<<< HEAD
=======
import org.hibernate.exception.DataException;
>>>>>>> branch 'master' of https://github.com/CBSIUFAC/SisProBom-AC.git
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import DAO.UsuarioDAO;
import entity.Funcionario;
import entity.Usuario;

@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	private UsuarioDAO dao = new UsuarioDAO();
	private List<Usuario> lista = null;
<<<<<<< HEAD
	private List<Usuario> filtro = null;
=======
>>>>>>> branch 'master' of https://github.com/CBSIUFAC/SisProBom-AC.git
	private Usuario[] selecionados;
	private List<Usuario> filtro = null;
	
	
	public Usuario getUsuario() {
		if(usuario== null)
			usuario = new Usuario();
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void salvarUsuario() {
<<<<<<< HEAD
=======
	
>>>>>>> branch 'master' of https://github.com/CBSIUFAC/SisProBom-AC.git
		try {
			String textoMsg = null;
			if (usuario.getId() == 0) {
<<<<<<< HEAD
				String senha = usuario.getSenha();
				usuario.setSenha(criptografarSenha(senha));
=======
				usuario.setSenha(criptografarSenha(usuario.getSenha()));
>>>>>>> branch 'master' of https://github.com/CBSIUFAC/SisProBom-AC.git
				dao.inserirUsuario(usuario);
				textoMsg = "Registro incluído com sucesso!";
			} else {
				dao.atualizarUsuario(usuario);
				textoMsg = "Registro alterado com sucesso!";
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
				selecionados = null;
			}
			lista = null;
			usuario = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void deletarUsuarios() {
		if (selecionados.length > 0) {
			try {
				for (Usuario usuario : selecionados) {
					dao.deletarUsuario(usuario);
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
	
	public List<Usuario> getLista() {
		if(lista == null)
			lista = dao.getListaUsuario();
		return lista;
	}
	
	public List<Usuario> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<Usuario> filtro) {
		this.filtro = filtro;
	}

	public Usuario[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Usuario[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		usuario = (Usuario) e.getObject();
	}
	
	public void cancelar() {
		usuario = null;
	}
	
	private String senhaCriptografada;
<<<<<<< HEAD

	public String criptografarSenha(String senha){
		String sc = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02x", b));
			}
			sc = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sc;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}
	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}
=======
>>>>>>> branch 'master' of https://github.com/CBSIUFAC/SisProBom-AC.git

	public String criptografarSenha(String senha){
		String sc = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02x", b));
			}
			sc = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sc;
	}
	//mudança
	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}
	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

}
