package br.com.atech.notafiscal.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.notafiscal.emitente.model.Emitente;

@Entity
@Where( clause = "ativo = true" )
@SQLDelete(sql = "update Nota_fiscal set ativo = false where id = ?")
@Table( name = "NOTA_FISCAL" )
@SequenceGenerator( sequenceName = "SEQ_NOTA_FISCAL", name = "SEQ_NOTA_FISCAL", allocationSize = 1 )
public class NotaFiscal implements ObjectConsumer{
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_NOTA_FISCAL")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "emitente_id", referencedColumnName = "id")
	private Emitente emitente;
	
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable( name = "nota_mercadoria", joinColumns = {@JoinColumn( name = "nota_id", referencedColumnName = "id" ) },
		inverseJoinColumns = { @JoinColumn( name = "mercadoria_id", referencedColumnName = "id" ) } )
	private Set<Mercadoria>mercadorias;
	
	@Column( name="valor_total" )
	private BigDecimal valorTotal;
	
	
	@Column( name="data_emissao" )
	@Temporal(TemporalType.DATE)
	private Calendar dataEmissao;
	
	
	@Column( name="nro_nota" )
	private Integer nroNota;
	
	private boolean ativo = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Emitente getEmitente() {
		return emitente;
	}

	public void setEmitente(Emitente emitente) {
		this.emitente = emitente;
	}

	public Set<Mercadoria> getMercadorias() {
		return mercadorias;
	}

	public void setMercadorias(Set<Mercadoria> mercadorias) {
		this.mercadorias = mercadorias;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getNroNota() {
		return nroNota;
	}

	public void setNroNota(Integer nroNota) {
		this.nroNota = nroNota;
	}

	@Override
	public String messageConsumer() {
		return "Invoice Number "+this.nroNota;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
