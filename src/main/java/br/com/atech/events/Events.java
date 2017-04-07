package br.com.atech.events;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "EVENTS" )
@SequenceGenerator( sequenceName = "SEQ_EVENTS", name = "SEQ_EVENTS", allocationSize = 1 )
public class Events {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_EVENTS")
	private Long id;
	
	
	@Column( name = "type_events" )
	@Enumerated( EnumType.STRING )
	private TypeEvents typeEvents;
	
	private String message;
	
	@Column( name = "status_event" )
	@Enumerated( EnumType.STRING )
	private StatusEvent statusEvent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeEvents getTypeEvents() {
		return typeEvents;
	}

	public void setTypeEvents(TypeEvents typeEvents) {
		this.typeEvents = typeEvents;
	}

	public StatusEvent getStatusEvent() {
		return statusEvent;
	}

	public void setStatusEvent(StatusEvent statusEvent) {
		this.statusEvent = statusEvent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
