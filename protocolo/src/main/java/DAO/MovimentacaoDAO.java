package DAO;

import java.util.List;

import entity.Documento;
import entity.Movimentacao;

public class MovimentacaoDAO extends MasterDAO {
	
	public void inserirMovimentacao(Movimentacao movimentacao) {
		inserirObjeto(movimentacao);
	}
	
	public void deletarMovimentacao(Movimentacao movimentacao) {
		deletarObjeto(movimentacao);
	}
	
	public void atualizarMovimentacao(Movimentacao movimentacao) {
		atualizarObjeto(movimentacao);
	}
	
	public Movimentacao getMovimentacao(int idMovimentacao) {
		return getObjeto(Movimentacao.class, idMovimentacao);
	}
	
	public List<Movimentacao> getListaMovimentacao() {
		return getLista("from Movimentacao m");
		
	}
	
	public List<Movimentacao> getListaMovimentacao(Documento documento) {
		return getLista("from Movimentacao m where m.documento = " + documento.getProtocolo());
		
	}
	
}
