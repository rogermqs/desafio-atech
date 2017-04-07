package br.com.atech.mercadoria.model;

import static br.com.atech.utils.QueueConstants.QUEUE_MERCADORIA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercadoriaService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MercadoriaService.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	public void save(Mercadoria mercadoria)
	{
		LOGGER.info("Mercadoria Enviada Para Fila");
		rabbitTemplate.convertAndSend(QUEUE_MERCADORIA, mercadoria);
	}
}
