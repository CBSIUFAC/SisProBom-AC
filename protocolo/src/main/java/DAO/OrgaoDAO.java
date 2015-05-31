package DAO;

import java.util.List;

import org.hibernate.exception.DataException;

import entity.Orgao;

public class OrgaoDAO extends MasterDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327934062328530452L;

	public void inserirOrgao(Orgao orgao) throws DataException {
		inserirObjeto(orgao);
	}
	
	public void deletarOrgao(Orgao orgao) {
		deletarObjeto(orgao);
	}
	
	public void atualizarOrgao(Orgao orgao) {
		atualizarObjeto(orgao);
	}
	
	public Orgao getOrgao(int idOrgao) {
		return getObjeto(Orgao.class, idOrgao);
	}
	
	public List<Orgao> getListaOrgao() {
		return getLista("from Orgao o");
	}
	
	public List<Orgao> getListaOrgaoInterno() {
		return getLista("from Orgao o where o.interno = 1");
	}
	
	public List<Orgao> getListaOrgaoExterno() {
		return getLista("from Orgao o where o.interno = 0");
	}
	
}
