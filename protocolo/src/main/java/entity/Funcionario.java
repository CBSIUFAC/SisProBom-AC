package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Funcionario implements Serializable {
	
	// Criado 1,2,3,4
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer  id_funcionario;
	@Column(nullable=false)
	private String nome;
	
	private Integer matricula;
	
	
	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="setor")
	private Setor setor;
	
	@OneToOne(mappedBy="funcionario")
	private Usuario usuario;

	
	public int getId() {
		
		return id_funcionario;
	}

	public void setId(int id) {
		this.id_funcionario = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_funcionario == null) ? 0 : id_funcionario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id_funcionario == null) {
			if (other.id_funcionario != null)
				return false;
		} else if (!id_funcionario.equals(other.id_funcionario))
			return false;
		return true;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	
	
}
