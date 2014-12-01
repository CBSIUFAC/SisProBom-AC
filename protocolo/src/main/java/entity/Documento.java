package entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Documento {
	
	@Id
	private int protocolo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data;
	@Column(nullable=false)
	private String assunto;
	@Column(nullable=false)
	private String origem;
	@Column(nullable=false)
	private boolean arquivado;
	@Column(nullable=false)
	private String integra;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="protocolista",nullable=false)
	private Usuario protocolista;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="tipo_documento",nullable=false)
	private TipoDocumento tipo_documento;
	
	@OneToMany(mappedBy="documento")
	private List<Movimentacao> movimentacoes;
	//metodos getters and setters
}
