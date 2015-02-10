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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private int matricula;

	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="setor")
	private Orgao setor;
	
	@OneToOne(mappedBy="funcionario")
	/*@OneToOne
	@JoinColumn(referencedColumnName ="id",name="usuario")*/
	private Usuario usuario;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Orgao getSetor() {
		return setor;
	}

	public void setSetor(Orgao setor) {
		this.setor = setor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Funcionario) && (id != null) ? id.equals(((Funcionario) obj).id) : (obj == this);
	}
	
}
