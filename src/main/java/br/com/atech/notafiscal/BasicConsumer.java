package br.com.atech.notafiscal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.atech.events.Events;
import br.com.atech.events.StatusEvent;
import br.com.atech.events.TypeEvents;
import br.com.atech.events.model.EventsRepository;
import br.com.atech.notafiscal.model.ObjectConsumer;

@Component
public abstract class BasicConsumer<T extends ObjectConsumer> {
	
	@Autowired
	private EventsRepository eventsRepository;
	
	public abstract void handleMessage( final T objectConsumer );
	
	
	public void saveEvents(ObjectConsumer objectConsumer, TypeEvents typeEvents)
	{
		Events events = new Events();
		events.setMessage(objectConsumer.messageConsumer()+ typeEvents.message);
		events.setStatusEvent(StatusEvent.UNREAD);
		events.setTypeEvents(typeEvents);
		eventsRepository.save(events);
	}
	

}
