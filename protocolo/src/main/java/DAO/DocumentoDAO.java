package DAO;

import java.util.List;

import entity.Documento;

public class DocumentoDAO extends MasterDAO {
	
	public void inserirDocumento(Documento documento) {
		inserirObjeto(documento);
	}
	
	public void deletarDocumento(Documento documento) {
		deletarObjeto(documento);
	}
	
	public void atualizarDocumento(Documento documento) {
		atualizarObjeto(documento);
	}
	
	public Documento getDocumento(int idDocumento) {
		return getObjeto(Documento.class, idDocumento);
	}
	
	public List<Documento> getListaDocumento() {
		return getLista("from Documento d");
	}

}
