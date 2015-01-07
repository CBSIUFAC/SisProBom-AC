package DAO;

import java.util.List;

import entity.Usuario;

public class UsuarioDAO extends MasterDAO {
	public void inserirUsuario(Usuario usuario) {
		inserirObjeto(usuario);
	}
	
	public void deletarUsuario(Usuario usuario) {
		deletarObjeto(usuario);
	}
	
	public void atualizarUsuario(Usuario usuario) {
		atualizarObjeto(usuario);
	}
	
	public Usuario getUsuario(int idUsuario) {
		return getObjeto(Usuario.class, idUsuario);
	}
	
	public List<Usuario> getListaUsuario() {
		return getLista("from Usuario u");
		
	}

}
