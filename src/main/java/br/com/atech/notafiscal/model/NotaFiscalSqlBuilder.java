package br.com.atech.notafiscal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.atech.mercadoria.model.MercadoriaMock;
import br.com.atech.notafiscal.FiltroNotaFiscal;
import br.com.atech.notafiscal.emitente.model.EmitenteMock;

public class NotaFiscalSqlBuilder {
	
	private final List<Predicate> predicates;
	private final FiltroNotaFiscal request;
	private final CriteriaBuilder builder;
	private final Root<NotaFiscalMock> root;
	private final CriteriaQuery<?> query;
	
	public NotaFiscalSqlBuilder(final FiltroNotaFiscal request, final CriteriaBuilder builder, final Root<NotaFiscalMock> root, final CriteriaQuery<?> query) {
		this.predicates = new ArrayList<Predicate>();
		this.request = request;
		this.builder = builder;
		this.root = root;
		this.query = query;
	}
	
	
	public NotaFiscalSqlBuilder searchByEmitente() {
		if( Objects.nonNull(request.getIdEmitente()) )
		{
			Join<NotaFiscalMock, EmitenteMock> ownerNota = root.join("emitente");
			this.predicates.add( builder.equal( ownerNota.get("id"), request.getIdEmitente()));
		}
		return this;
	}
	
	public NotaFiscalSqlBuilder searchByMercadoria() {
		if( Objects.nonNull(request.getIdMercadoria()) )
		{
			Join<NotaFiscalMock, MercadoriaMock> ownerNota = root.join("mercadorias");
			this.predicates.add( builder.equal( ownerNota.get("id"), request.getIdMercadoria()));
		}
		return this;
	}
	
	public List<Predicate> build() {
		return predicates;
	}
}
