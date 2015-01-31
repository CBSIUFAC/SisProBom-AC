package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import DAO.MovimentacaoDAO;
import entity.Movimentacao;

@ManagedBean(name="movimentacaoBean")
@SessionScoped
public class MovimentacaoBean implements Serializable {
	
	private Movimentacao movimentacao;
	private MovimentacaoDAO dao = new MovimentacaoDAO();
	private List<Movimentacao> lista = null;
	private List<Movimentacao> filtro = null;
	private Movimentacao[] selecionados;
	
	public Movimentacao getMovimentacao() {
		if(movimentacao == null)
			movimentacao = new Movimentacao();
		return movimentacao;
	}
	
	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	public void salvarMovimentacao() {
		try {
			String textoMsg = null;
			dao.inserirMovimentacao(movimentacao);
			textoMsg = "Registro incluído com sucesso!";
			RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
//			if (movimentacao.getId() == 0) {
//				dao.inserirMovimentacao(movimentacao);
//				textoMsg = "Registro incluído com sucesso!";
//			} else {
//				dao.atualizarMovimentacao(movimentacao);
//				textoMsg = "Registro alterado com sucesso!";
//				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
//				selecionados = null;
//			}
			lista = null;
			movimentacao = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
//	public void deletarMovimentacoes() {
//		if (selecionados.length > 0) {
//			try {
//				for (Movimentacao movimentacao : selecionados) {
//					dao.deletarMovimentacao(movimentacao);
//				}
//				lista = null;
//				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro removido com sucesso!");
//				FacesContext.getCurrentInstance().addMessage(null, msg);
//			} catch (JDBCException e) {
//				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
//				FacesContext.getCurrentInstance().addMessage(null, msg);
//			}
//		}
//	}
	
	public List<Movimentacao> getLista() {
		if(lista == null)
			lista = dao.getListaMovimentacao();
		return lista;
	}
	
	public List<Movimentacao> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<Movimentacao> filtro) {
		this.filtro = filtro;
	}

	public Movimentacao[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Movimentacao[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		movimentacao = (Movimentacao) e.getObject();
	}
	
	public void cancelar() {
		movimentacao = null;
	}

}
