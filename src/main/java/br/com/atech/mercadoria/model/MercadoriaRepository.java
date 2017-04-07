package br.com.atech.mercadoria.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long>, JpaSpecificationExecutor<Mercadoria>{
	
	
	@Query(value = "SELECT p FROM Mercadoria p where UPPER(p.nome) like CONCAT('%',UPPER(:nomeOrCode),'%')   or p.codigo=:nomeOrCode")
	public List<Mercadoria> findByCodigoSistemaOrDescricaoIgnoreCaseContaining(@Param("nomeOrCode") String descricao);

}
