package br.com.atech.mercadoria;

import java.math.BigDecimal;

public class FiltroMercadoria {
	
	
	
	private String nome;
	
	private String codigo;
	
	private String codigoOuNome;
	
	private BigDecimal preco;

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

	public String getCodigoOuNome() {
		return codigoOuNome;
	}

	public void setCodigoOuNome(String codigoOuNome) {
		this.codigoOuNome = codigoOuNome;
	}
	
	

}
