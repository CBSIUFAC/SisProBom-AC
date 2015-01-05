package DAO;

import java.util.List;

import entity.Setor;

public class SetorDAO extends MasterDAO {

	public void inserirSetor(Setor setor) {
		inserirObjeto(setor);
	}
	
	public void deletarSetor(Setor setor) {
		deletarObjeto(setor);
	}
	
	public void atualizarSetor(Setor setor) {
		atualizarObjeto(setor);
	}
	
	public Setor getSetor(int idSetor) {
		return getObjeto(Setor.class, idSetor);
	}
	
	public List<Setor> getListaSetor() {
		return getLista("from Setor s");
	}
	//
	
}
