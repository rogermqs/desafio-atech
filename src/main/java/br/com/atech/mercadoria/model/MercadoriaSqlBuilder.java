package br.com.atech.mercadoria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.atech.mercadoria.FiltroMercadoria;

public class MercadoriaSqlBuilder {
	
	private final List<Predicate> predicates;
	private final FiltroMercadoria request;
	private final CriteriaBuilder builder;
	private final Root<Mercadoria> root;
	
	public MercadoriaSqlBuilder(final FiltroMercadoria request, final CriteriaBuilder builder, final Root<Mercadoria> root) {
		this.predicates = new ArrayList<Predicate>();
		this.request = request;
		this.builder = builder;
		this.root = root;
	}
	
	
	public MercadoriaSqlBuilder searchNome() {
		if( StringUtils.isNotBlank(request.getNome()) )
		{
			this.predicates.add(builder.equal(root.get("nome"), request.getNome()));
		}
		return this;
	}
	
	public MercadoriaSqlBuilder searchPreco() {
		if( Objects.nonNull(request.getPreco()) )
		{
			this.predicates.add(builder.equal(root.get("preco"), request.getPreco()));
		}
		return this;
	}
	
	public MercadoriaSqlBuilder searchCodigo() {
		if( StringUtils.isNotBlank(request.getCodigo()) )
		{
			this.predicates.add(builder.equal(root.get("codigo"), request.getCodigo()));
		}
		return this;
	}
	
	public List<Predicate> build() {
		return predicates;
	}
}
