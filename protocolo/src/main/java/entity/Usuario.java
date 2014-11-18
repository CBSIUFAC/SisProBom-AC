package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String senha;
	@Column(nullable=false)
	private int perfil;
	
	@OneToOne
	@JoinColumn(referencedColumnName="matricula",name="funcionario",nullable=false)
	private Funcionario funcionario;
	
	@OneToMany(mappedBy="protocolista")
	private List<Documento> documentos;

}
