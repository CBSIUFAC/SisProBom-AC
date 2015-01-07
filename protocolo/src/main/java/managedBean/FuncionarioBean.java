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

import DAO.FuncionarioDAO;
import entity.Funcionario;

@ManagedBean(name="funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {
	
	private Funcionario funcionario;
	private FuncionarioDAO dao = new FuncionarioDAO();
	private List<Funcionario> listaFuncionario = null;
	private Funcionario[] selecionados;
	
	public Funcionario getFuncionario() {
		if(funcionario == null)
			funcionario = new Funcionario();
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void inserirFuncionario() {
		try {
			dao.inserirFuncionario(funcionario);
			listaFuncionario = null;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (JDBCException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getSQLException().getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		//return "funcionario";
	}
	
	public String deletarFuncionario() {
		dao.deletarFuncionario(funcionario);
		listaFuncionario = null;
		return "funcionario";
	}
	
	public void deletarFuncionarios(ActionEvent event) {
		if (selecionados.length > 0) {
			for (Funcionario funcionario : selecionados) {
				dao.deletarFuncionario(funcionario);
			}
		}
	}
	
	public List<Funcionario> getListaFuncionarios() {
		if(listaFuncionario == null)
			listaFuncionario = dao.getListaFuncionario();
		return listaFuncionario;
	}

	public Funcionario[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Funcionario[] selecionados) {
		this.selecionados = selecionados;
	}

}
