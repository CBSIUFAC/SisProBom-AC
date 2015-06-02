package managedBean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import DAO.UsuarioDAO;
import entity.Usuario;

@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1859947848133113112L;
	
	private Usuario usuario;
	private UsuarioDAO dao = new UsuarioDAO();
	private List<Usuario> lista = null;
	private List<Usuario> filtro = null;
	private Usuario[] selecionados;
	private String senhaCriptografada;
	
	public Usuario getUsuario() {
		if(usuario== null)
			usuario = new Usuario();
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void salvarUsuario() {
		try {
			String textoMsg = null;
			if (usuario.getId() == 0) {
				String senha = usuario.getSenha();
				usuario.setSenha(criptografarSenha(senha));
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
	
	public String getUsuarioAutenticado() {
		
		String usuario = null;
		Authentication autenticao = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(autenticao instanceof AnonymousAuthenticationToken)) {
			usuario = autenticao.getName();
		}
		
		return usuario;
		
	}
	
	public String getUsuarioAutenticadoPerfil() {
		
		String perfil = null;
		String nomePerfil = null;
		Authentication autenticao = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(autenticao instanceof AnonymousAuthenticationToken)) {
			
			perfil = autenticao.getAuthorities().toArray()[0].toString();
			
			if (perfil.equals("1")) {
				nomePerfil = "Administrador";
			} else if (perfil.equals("2")) {
				nomePerfil = "Usuário comum";
			}
			
		}
		
		return nomePerfil;
		
	}

}
