package entity;

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
public class Setor {//implements Serializable
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="vinculo",nullable=false)
	private Setor vinculo;
	
	@OneToMany(mappedBy="vinculo")
	private List<Setor> setores;
	
	@OneToMany(mappedBy="setor_destino")
	private List<Movimentacao> movimentacoes;
	
	@OneToMany(mappedBy="setor")
	private List<Funcionario> funcionarios;
	//metodos getters and setters
}
