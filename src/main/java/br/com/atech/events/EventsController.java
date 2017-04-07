package br.com.atech.events;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atech.events.model.EventsRepository;

@RestController
@Transactional
@RequestMapping( "/rest/events" )
public class EventsController {
	
	@Autowired
	private EventsRepository eventsRepository;
	
	@GetMapping( value="/unread" )
    public List<Events> events() throws InterruptedException  {
		return eventsRepository.findByStatusEvent(StatusEvent.UNREAD);
	}
	
	@GetMapping(value = "/read/{id}")
	public void readEvents( @PathVariable final Long id ) {
		eventsRepository.readEvent( id );
	}

}
