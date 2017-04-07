package br.com.atech.notafiscal.emitente.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table( name = "EMITENTE" )
@SequenceGenerator( sequenceName = "SEQ_EMITENTE", name = "SEQ_EMITENTE", allocationSize = 1 )
public class Emitente {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_EMITENTE")
	private Long id;
	
	private String nome;
	
	private String documento;

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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
