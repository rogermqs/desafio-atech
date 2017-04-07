package br.com.atech.mercadoria;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;


public class MercadoriaJson implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	
	
	@NotNull
	private String nome;
	
	@NotNull
	private String codigo;
	
	@NotNull
	private BigDecimal preco;
	
	
	private boolean ativo = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
