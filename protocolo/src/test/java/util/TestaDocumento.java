package util;

import java.util.List;

import com.lowagie.text.Document;

import entity.Documento;
import entity.Funcionario;
import entity.Setor;
import DAO.DocumentoDAO;
import DAO.FuncionarioDAO;
import DAO.TipoDocumentoDAO;

public class TestaDocumento {

	public static void main(String[] args) {
		
		DocumentoDAO dao = new DocumentoDAO();
		FuncionarioDAO daoFuncionario = new FuncionarioDAO();
		TipoDocumentoDAO daoTipoDocumento = new TipoDocumentoDAO();
		
		//Inserir
		Documento d1 = new Documento();
		d1.setAssunto("Teste 2");
		d1.setOrigem("Órgão interno");
		d1.setProtocolista(daoFuncionario.getFuncionario(1));
		d1.setTipoDocumento(daoTipoDocumento.getTipoDocumento(1));
		dao.inserirDocumento(d1);
		
		//Listar
		List<Documento> documentos = dao.getListaDocumento();
		for (Documento documento : documentos) {
			System.out.println(documento.getAssunto() + " " + documento.getData());
		}
		
	}
	
}
