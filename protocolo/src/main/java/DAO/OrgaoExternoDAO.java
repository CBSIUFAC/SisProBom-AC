package DAO;

import java.util.List;

import org.hibernate.exception.DataException;

import entity.OrgaoExterno;


public class OrgaoExternoDAO extends MasterDAO {
	
	public void inserirOrgao(OrgaoExterno orgao) throws DataException {
		inserirObjeto(orgao);
	}
	
	public void deletarOrgao(OrgaoExterno orgao) {
		deletarObjeto(orgao);
	}
	
	public void atualizarOrgao(OrgaoExterno orgao) {
		atualizarObjeto(orgao);
	}
	
	public OrgaoExterno getOrgao(int idOrgao) {
		return getObjeto(OrgaoExterno.class, idOrgao);
	}
	
	public List<OrgaoExterno> getListaOrgao() {
		return getLista("from OrgaoExterno o");
	}
	
}

