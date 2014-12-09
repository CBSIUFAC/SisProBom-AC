package entity;

import java.io.Serializable;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_movimentacao() {
		return data_movimentacao;
	}

	public void setData_movimentacao(Date data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
	}

	public Date getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
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

	public Setor getSetor_destino() {
		return setor_destino;
	}

	public void setSetor_destino(Setor setor_destino) {
		this.setor_destino = setor_destino;
	}
	
}
