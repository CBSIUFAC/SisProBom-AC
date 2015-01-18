package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Setor implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false)
	private String nome;
	
	/* A idéia era manter uma hierarquia entre setores,
	 * mas vaos buscar uma forma melhor de fazer isso
	 * @ManyToOne
	@JoinColumn(referencedColumnName="id",name="vinculo",nullable=false)
	private Setor vinculo;
	
	@OneToMany(mappedBy="vinculo")
	private List<Setor> setores;*/
	
	@OneToMany(mappedBy="setor_destino")
	private List<Movimentacao> movimentacoes;
	
	@OneToMany(mappedBy="setor")
	private List<Funcionario> funcionarios;

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

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Setor) && (id != null) ? id.equals(((Setor) obj).id) : (obj == this);
	}

}
