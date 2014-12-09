package entity;

import java.io.Serializable;
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
public class Documento implements Serializable {
	
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

	public int getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(int protocolo) {
		this.protocolo = protocolo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public boolean isArquivado() {
		return arquivado;
	}

	public void setArquivado(boolean arquivado) {
		this.arquivado = arquivado;
	}

	public String getIntegra() {
		return integra;
	}

	public void setIntegra(String integra) {
		this.integra = integra;
	}

	public Usuario getProtocolista() {
		return protocolista;
	}

	public void setProtocolista(Usuario protocolista) {
		this.protocolista = protocolista;
	}

	public TipoDocumento getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(TipoDocumento tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

}
