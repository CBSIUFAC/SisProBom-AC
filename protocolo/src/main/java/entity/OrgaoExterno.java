package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrgaoExterno implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false)
	private String nome;
	

//	
//	@OneToMany(mappedBy="orgao_destino")
//	private List<Movimentacao> movimentacoes;
//	
//	@OneToMany(mappedBy="orgao")
//	private List<Funcionario> funcionarios;

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
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof OrgaoExterno) && (id != null) ? id.equals(((OrgaoExterno) obj).id) : (obj == this);
	}

}