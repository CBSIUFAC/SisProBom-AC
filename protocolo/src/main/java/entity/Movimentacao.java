package entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class Movimentacao implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataMovimentacao = new Timestamp((new Date()).getTime());
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dataRecebimento;
	@Column(nullable=false)
	private boolean recebido = false;
	@Column
	private String despacho;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="protocolo",name="documento",nullable=false)
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="setorDestino",nullable=false)
	private Orgao setorDestino;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public boolean isRecebido() {
		return recebido;
	}

	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Orgao getSetorDestino() {
		return setorDestino;
	}

	public void setSetorDestino(Orgao setorDestino) {
		this.setorDestino = setorDestino;
	}
	
}
