package br.com.atech.mercadoria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.atech.notafiscal.model.ObjectConsumer;


@Entity
@Table( name = "MERCADORIA" )
@SequenceGenerator( sequenceName = "SEQ_MERCADORIA", name = "SEQ_MERCADORIA", allocationSize = 1 )
public class Mercadoria implements ObjectConsumer{
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_MERCADORIA")
	private Long id;
	
	private String nome;
	
	private String codigo;
	
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

	@Override
	public String messageConsumer() {
		return "Products "+this.nome;
	}

}
