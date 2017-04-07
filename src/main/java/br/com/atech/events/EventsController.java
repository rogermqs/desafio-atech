package br.com.atech.events;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		List<Events> listEvents = eventsRepository.findByStatusEvent(StatusEvent.UNREAD);
		if(CollectionUtils.isNotEmpty(listEvents))
		{
			eventsRepository.readEvent(listEvents.stream().map(Events::getId).collect(Collectors.toList()));
		}
		return listEvents;
	}
	
}
