package managedBean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	private Movimentacao selecionado;

	@ManagedProperty(value="#{documentoBean}")
	private DocumentoBean documentoBean;
	
	public DocumentoBean getDocumentoBean() {
		return documentoBean;
	}

	public void setDocumentoBean(DocumentoBean documentoBean) {
		this.documentoBean = documentoBean;
	}

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
			movimentacao.setDocumento(documentoBean.getDocumento());
			dao.inserirMovimentacao(movimentacao);
			textoMsg = "Documento movimentado com sucesso!";
			RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
			lista = null;
			movimentacao = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	/*
	 * Este método, diferente das outras classes, não verifica se alista está nula.
	 * O motivo é que ao listar as movimentações é necessário buscar as informações por documento,
	 * e assim se já houver uma listar, esta pode mostrar movimentações de outro documento consultado
	 * anteriormente.
	 * 
	 * */
	public List<Movimentacao> getLista() {
		lista = dao.getListaMovimentacao(documentoBean.getDocumento());
		return lista;
	}
	
	public List<Movimentacao> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<Movimentacao> filtro) {
		this.filtro = filtro;
	}

	public Movimentacao getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Movimentacao selecionado) {
		this.selecionado = selecionado;
	}
	
	public void preparaEdicao(SelectEvent e) {
		movimentacao = (Movimentacao) e.getObject();
	}
	
	public void cancelar() {
		movimentacao = null;
	}
	
	public String voltar() {
		documentoBean.setDocumento(null);
		documentoBean.setSelecionados(null);
		return "documento";
	}
	
	public void inserir() {
		if (!documentoBean.getDocumento().isArquivado()) {
			if (lista.size() > 0) {
				Movimentacao ultimaMovimentacao = lista.get(lista.size() - 1);
				if (ultimaMovimentacao.isRecebido()) {
					RequestContext.getCurrentInstance().execute("PF('dlgCadastro').show()");
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "A última movimentação não foi recebida.");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').show()");
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Documentos arquivados não podem ser movimentados.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void confirmar() {
		Movimentacao ultimaMovimentacao = lista.get(lista.size() - 1);
		ultimaMovimentacao.setRecebido(true);
		ultimaMovimentacao.setDataRecebimento(new Timestamp((new Date()).getTime()));
		dao.atualizarMovimentacao(ultimaMovimentacao);
		lista = null;
		selecionado = null;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Recebimento confirmado.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void recusar() {
		if (lista.size() > 1) {
			Movimentacao penultimaMovimentacao = lista.get(lista.size() - 2);
			movimentacao.setSetorDestino(penultimaMovimentacao.getSetorDestino());
			movimentacao.setDespacho("Recebimento recusado");
			movimentacao.setDocumento(documentoBean.getDocumento());
			dao.inserirMovimentacao(movimentacao);
		} else if (lista.size() > 0) {
			movimentacao.setSetorDestino(documentoBean.getDocumento().getOrigem());
			movimentacao.setDespacho("Recebimento recusado");
			movimentacao.setDocumento(documentoBean.getDocumento());
			dao.inserirMovimentacao(movimentacao);
		}
	}

}
