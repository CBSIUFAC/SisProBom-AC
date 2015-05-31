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

import DAO.FuncionarioDAO;
import entity.Funcionario;

@ManagedBean(name="funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8607116946061653251L;
	
	private Funcionario funcionario;
	private FuncionarioDAO dao = new FuncionarioDAO();
	private List<Funcionario> lista = null;
	private List<Funcionario> filtro = null;
	private Funcionario[] selecionados;
	
	public Funcionario getFuncionario() {
		if(funcionario == null)
			funcionario = new Funcionario();
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void salvarFuncionario() {
		try {
			String textoMsg = null;
			if (funcionario.getId() == null) {
				dao.inserirFuncionario(funcionario);
				textoMsg = "Registro incluído com sucesso!";
			} else {
				dao.atualizarFuncionario(funcionario);
				textoMsg = "Registro alterado com sucesso!";
				RequestContext.getCurrentInstance().execute("PF('dlgCadastro').hide()");
				selecionados = null;
			}
			lista = null;
			funcionario = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, textoMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void deletarFuncionarios() {
		if (selecionados.length > 0) {
			try {
				for (Funcionario funcionario : selecionados) {
					dao.deletarFuncionario(funcionario);
				}
				lista = null;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro removido com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (JDBCException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public List<Funcionario> getLista() {
		if(lista == null)
			lista = dao.getListaFuncionario();
		return lista;
	}
	
	public List<Funcionario> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<Funcionario> filtro) {
		this.filtro = filtro;
	}

	public Funcionario[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Funcionario[] selecionados) {
		this.selecionados = selecionados;
	}
	
	public void preparaEdicao(SelectEvent e) {
		funcionario = (Funcionario) e.getObject();
	}
	
	public void cancelar() {
		funcionario = null;
	}

}
