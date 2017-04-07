package br.com.atech.notafiscal;

import static br.com.atech.utils.QueueConstants.CONTAINER;
import static br.com.atech.utils.QueueConstants.QUEUE_NOTA_FISCAL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.atech.events.TypeEvents;
import br.com.atech.notafiscal.model.NotaFiscal;
import br.com.atech.notafiscal.model.NotaFiscalRepository;;

@Component
public class NotaFiscalConsumer extends BasicConsumer<NotaFiscal>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotaFiscalConsumer.class);
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	
	@RabbitListener( queues = QUEUE_NOTA_FISCAL, containerFactory = CONTAINER)
	public void handleMessage( final NotaFiscal notaFiscal ) {
		
		try {
			LOGGER.info("Fila de Nota Fiscal Consumida");
			notaFiscalRepository.save(notaFiscal);
			saveEvents(notaFiscal, TypeEvents.SUCCESS);
		} catch (final Exception exception) {
			saveEvents(notaFiscal, TypeEvents.ERROR);
		}
	}

}
