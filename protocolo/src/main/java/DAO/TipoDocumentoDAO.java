package DAO;

import java.util.List;

import org.hibernate.exception.DataException;

import entity.TipoDocumento;

public class TipoDocumentoDAO extends MasterDAO {

	public void inserirTipoDocumento(TipoDocumento tipoDocumento) throws DataException {
		inserirObjeto(tipoDocumento);
	}
	
	public void deletarTipoDocumento(TipoDocumento tipoDocumento) {
		deletarObjeto(tipoDocumento);
	}
	
	public void atualizarTipoDocumento(TipoDocumento tipoDocumento) {
		atualizarObjeto(tipoDocumento);
	}
	
	public TipoDocumento getTipoDocumento(int idTipoDocumento) {
		return getObjeto(TipoDocumento.class, idTipoDocumento);
	}
	
	public List<TipoDocumento> getListaTipoDocumento() {
		return getLista("from TipoDocumento s");
	}
	
}
