package br.com.atech.notafiscal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.notafiscal.FiltroNotaFiscal;
import br.com.atech.notafiscal.emitente.model.Emitente;

public class NotaFiscalSqlBuilder {
	
	private final List<Predicate> predicates;
	private final FiltroNotaFiscal request;
	private final CriteriaBuilder builder;
	private final Root<NotaFiscal> root;
	
	public NotaFiscalSqlBuilder(final FiltroNotaFiscal request, final CriteriaBuilder builder, final Root<NotaFiscal> root) {
		this.predicates = new ArrayList<Predicate>();
		this.request = request;
		this.builder = builder;
		this.root = root;
	}
	
	
	public NotaFiscalSqlBuilder searchByEmitente() {
		if( Objects.nonNull(request.getIdEmitente()) )
		{
			Join<NotaFiscal, Emitente> ownerNota = root.join("emitente");
			this.predicates.add( builder.equal( ownerNota.get("id"), request.getIdEmitente()));
		}
		return this;
	}
	
	public NotaFiscalSqlBuilder searchByMercadoria() {
		if( Objects.nonNull(request.getIdMercadoria()) )
		{
			Join<NotaFiscal, Mercadoria> ownerNota = root.join("mercadorias");
			this.predicates.add( builder.equal( ownerNota.get("id"), request.getIdMercadoria()));
		}
		return this;
	}
	
	public List<Predicate> build() {
		return predicates;
	}
}
