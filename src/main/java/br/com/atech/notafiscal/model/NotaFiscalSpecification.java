package br.com.atech.notafiscal.model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.atech.notafiscal.FiltroNotaFiscal;

public class NotaFiscalSpecification {
	
	
	public static Specification<NotaFiscalMock> searchNotaFiscal(final FiltroNotaFiscal filtro)  {

		return new Specification<NotaFiscalMock>() {
			@Override
			public Predicate toPredicate(Root<NotaFiscalMock> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				NotaFiscalSqlBuilder notaFiscalSqlBuilder = new NotaFiscalSqlBuilder(filtro, cb, root, query);
				List<Predicate> predicates = notaFiscalSqlBuilder.searchByEmitente()
																 .searchByMercadoria()
																 .build();
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

}
