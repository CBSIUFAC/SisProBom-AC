package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8202747089857954459L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String senha;
	@Column(nullable=false)
	private int perfil;
	@Column(nullable=false)
	private boolean ativo = true;
	
	@OneToOne
	@JoinColumn(referencedColumnName="id",name="funcionario",nullable=false)
	private Funcionario funcionario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
