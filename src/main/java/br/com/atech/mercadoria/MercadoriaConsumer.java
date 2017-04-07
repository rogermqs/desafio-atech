package br.com.atech.mercadoria;

import static br.com.atech.utils.QueueConstants.CONTAINER;
import static br.com.atech.utils.QueueConstants.QUEUE_MERCADORIA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.atech.events.TypeEvents;
import br.com.atech.mercadoria.model.Mercadoria;
import br.com.atech.mercadoria.model.MercadoriaRepository;
import br.com.atech.notafiscal.BasicConsumer;;

@Component
public class MercadoriaConsumer extends BasicConsumer<Mercadoria>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MercadoriaConsumer.class);
	
	@Autowired
	private MercadoriaRepository mercadoriaRepository;
	
	
	@RabbitListener( queues = QUEUE_MERCADORIA, containerFactory = CONTAINER)
	public void handleMessage( Mercadoria mercadoria ) {
		
		try {
			LOGGER.info("Fila de Mercadoria Consumida");
			mercadoriaRepository.save(mercadoria);
			saveEvents(mercadoria, TypeEvents.SUCCESS);
		} catch (final Exception exception) {
			saveEvents(mercadoria, TypeEvents.ERROR);
		}
	}
}
