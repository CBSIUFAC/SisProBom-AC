package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data_movimentacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data_recebimento;
	@Column(nullable=false)
	private boolean recebido;
	@Column(nullable=false)
	private String despacho;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="protocolo",name="documento",nullable=false)
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="setor",nullable=false)
	private Setor setor_destino;

}
