package DAO;

import java.util.List;

import entity.Funcionario;

public class FuncionarioDAO extends MasterDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4312484506637637243L;

	public void inserirFuncionario(Funcionario funcionario) {
		inserirObjeto(funcionario);
	}
	
	public void deletarFuncionario(Funcionario funcionario) {
		deletarObjeto(funcionario);
	}
	
	public void atualizarFuncionario(Funcionario funcionario) {
		atualizarObjeto(funcionario);
	}
	
	public Funcionario getFuncionario(int id) {
		return getObjeto(Funcionario.class, id);
	}
	
	public List<Funcionario> getListaFuncionario() {
		return getLista("from Funcionario f");
	}

}
