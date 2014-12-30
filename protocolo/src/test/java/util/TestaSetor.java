package util;

import java.util.List;

import DAO.SetorDAO;
import entity.Setor;

public class TestaSetor {

	public static void main(String[] args) {

		SetorDAO dao = new SetorDAO();
		
		// Inserindo
//		Setor s1 = new Setor();
//		s1.setNome("Recursos Humanos");
//		dao.inserirSetor(s1);
//		
//		Setor s2 = new Setor();
//		s2.setNome("Financeiro");
//		dao.inserirSetor(s2);
		 
		// Removendo
//		Setor s3 = dao.getSetor(1);
//		System.out.println(s3.getNome());
//		dao.deletarSetor(s3);
		
		// Atualizando
		Setor s4 = dao.getSetor(2);
		s4.setNome("Jurídico");
		dao.atualizarSetor(s4);
		
		// Listando
		List<Setor> setores = dao.getListaSetor();
		for (Setor setor : setores) {
			System.out.println(setor.getNome());
		}

	}

}
