package br.com.atech.notafiscal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.notafiscal.emitente.model.Emitente;


public class NotaFiscalJson implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	
	@NotNull
	private Emitente emitente;
	
	
	@NotEmpty
	private Set<Mercadoria>mercadorias;
	
	@NotNull
	private Integer nroNota;
	
	@NotNull
	private Calendar dataEmissao;
	
	
	@NotNull
	private BigDecimal valorTotal;
	
	
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

	public Integer getNroNota() {
		return nroNota;
	}

	public void setNroNota(Integer nroNota) {
		this.nroNota = nroNota;
	}

	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
