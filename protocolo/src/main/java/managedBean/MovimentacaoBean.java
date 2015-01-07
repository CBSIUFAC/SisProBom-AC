package managedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.exception.DataException;

import DAO.MovimentacaoDAO;
import entity.Movimentacao;

@ManagedBean(name="movimentacaoBean")
@SessionScoped
public class MovimentacaoBean implements Serializable {
	
	private Movimentacao movimentacao;
	private MovimentacaoDAO dao = new MovimentacaoDAO();
	private List<Movimentacao> listaMovimentacao = null;
	private Movimentacao[] selecionados;
	
	public Movimentacao getMovimentacao() {
		if(movimentacao == null)
			movimentacao = new Movimentacao();
		return movimentacao;
	}
	
	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	public void inserirMovimentacao() {
		try {
			dao.inserirMovimentacao(movimentacao);
			listaMovimentacao = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		//return "Movimentacao";
	}
	
	public String deletarMovimentacao() {
		dao.deletarMovimentacao(movimentacao);
		listaMovimentacao = null;
		return "movimentacao";
	}
	
	public void deletarMovimentacoes(ActionEvent event) {
		if (selecionados.length > 0) {
			for (Movimentacao movimentacoes : selecionados) {
				dao.deletarMovimentacao(movimentacao);
			}
		}
	}
	
	public List<Movimentacao> getListaMovimentacoes() {
		if(listaMovimentacao == null)
			listaMovimentacao = dao.getListaMovimentacao();
		return listaMovimentacao;
	}

	public Movimentacao[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Movimentacao[] selecionados) {
		this.selecionados = selecionados;
	}

}
