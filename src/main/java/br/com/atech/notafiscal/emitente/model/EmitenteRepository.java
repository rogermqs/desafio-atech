package br.com.atech.notafiscal.emitente.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmitenteRepository extends JpaRepository<EmitenteMock, Long>{

}
