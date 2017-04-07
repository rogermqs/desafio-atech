package br.com.atech.notafiscal.model;

import static br.com.atech.utils.QueueConstants.QUEUE_NOTA_FISCAL;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void saveNota(NotaFiscal notaFiscal)
	{
		rabbitTemplate.convertAndSend(QUEUE_NOTA_FISCAL, notaFiscal);
	}

}
