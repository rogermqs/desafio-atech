package br.com.atech.events.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.atech.events.Events;
import br.com.atech.events.StatusEvent;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long>, JpaSpecificationExecutor<Events>{
	
	
	
	public List<Events>findByStatusEvent(StatusEvent statusEvent);
	
	
	@Modifying
	@Transactional
	@Query( "UPDATE Events e  SET e.statusEvent = 'READ' where e.id in (:idsEvent)")
	void readEvent( @Param( "idsEvent" ) List<Long> idsEvent );
	
}
