package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Documento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2238403775443958685L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int protocolo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data = new Timestamp((new Date()).getTime());
	@Column(nullable=false)
	private String assunto;
	@Column(nullable=false)
	private boolean interno = true;
	@Column(nullable=false)
	private boolean arquivado = false;
	@Column
	private String integra;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="protocolista"/*,nullable=false*/)
	private Funcionario protocolista;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="tipoDocumento",nullable=false)
	private TipoDocumento tipoDocumento;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id",name="origem")
	private Orgao origem;

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

//	public boolean isOrigem() {
//		return arquivado;
//	}
//
//	public void setOrigem(boolean origem) {
//		this.origem = origem;
//	}

	public boolean isInterno() {
		return interno;
	}

	public void setInterno(boolean interno) {
		this.interno = interno;
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

	public Funcionario getProtocolista() {
		return protocolista;
	}

	public void setProtocolista(Funcionario protocolista) {
		this.protocolista = protocolista;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

//	public Setor getOrigemInterna() {
//		return origemInterna;
//	}
//
//	public void setOrigemInterna(Setor origemInterna) {
//		this.origemInterna = origemInterna;
//	}
//
//	public OrgaoExterno getOrigemExterna() {
//		return origemExterna;
//	}
//
//	public void setOrigemExterna(OrgaoExterno origemExterna) {
//		this.origemExterna = origemExterna;
//	}
	
	public Orgao getOrigem() {
		return origem;
	}

	public void setOrigem(Orgao origem) {
		this.origem = origem;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

}
