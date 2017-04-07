package br.com.atech.mercadoria.model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.atech.mercadoria.FiltroMercadoria;

public class MercadoriaSpecification {
	
	
	public static Specification<Mercadoria> searchMercadoria(final FiltroMercadoria filtro)  {

		return new Specification<Mercadoria>() {
			@Override
			public Predicate toPredicate(Root<Mercadoria> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				MercadoriaSqlBuilder mercadoriaSqlBuilder = new MercadoriaSqlBuilder(filtro, cb, root, query);
				List<Predicate> predicates = mercadoriaSqlBuilder.searchCodigo()
																 .searchNome()
																 .searchPreco()
																 .build();
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

}
