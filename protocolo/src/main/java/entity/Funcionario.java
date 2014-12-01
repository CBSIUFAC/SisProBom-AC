package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Funcionario {//implements Serializable
	
	@Id
	private int matricula;
	@Column(nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="setor",nullable=false)
	private Setor setor;
	
	@OneToOne(mappedBy="funcionario")
	private Usuario usuario;
	//metodos getters and setters
}
