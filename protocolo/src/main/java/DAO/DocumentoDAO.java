package DAO;

import java.util.List;

import entity.Documento;

public class DocumentoDAO extends MasterDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8888788117458152578L;

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
