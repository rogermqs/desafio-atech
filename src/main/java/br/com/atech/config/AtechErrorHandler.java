package br.com.atech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import br.com.atech.events.Events;
import br.com.atech.events.StatusEvent;
import br.com.atech.events.TypeEvents;
import br.com.atech.events.model.EventsRepository;


@Component
public class AtechErrorHandler implements ErrorHandler{

	@Autowired
	private EventsRepository eventsRepository;
	
	@Override
	public void handleError(Throwable t) {
		Events events = new Events();
		events.setMessage(t.getMessage());
		events.setStatusEvent(StatusEvent.UNREAD);
		events.setTypeEvents(TypeEvents.ERROR);
		eventsRepository.save(events);
	}

}
