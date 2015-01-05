package DAO;

import java.util.List;

import entity.Funcionario;

public class FuncionarioDAO extends MasterDAO {
	
	public void inserirFuncionario(Funcionario funcionario) {
		inserirObjeto(funcionario);
	}
	
	public void deletarDocumento(Funcionario funcionario) {
		deletarObjeto(funcionario);
	}
	
	public void atualizarDocumento(Funcionario funcionario) {
		atualizarObjeto(funcionario);
	}
	
	public Funcionario getFuncionario(int Matricula) {
		return getObjeto(Funcionario.class, Matricula);
	}
	
	public List<Funcionario> getListaFuncionario() {
		return getLista("from Funcionario f");
	}
//
}
