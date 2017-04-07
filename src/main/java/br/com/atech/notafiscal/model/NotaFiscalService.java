package br.com.atech.notafiscal.model;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static br.com.atech.utils.QueueConstants.QUEUE_NOTA_FISCAL;

@Service
public class NotaFiscalService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void saveNota(NotaFiscalMock notaFiscal)
	{
		rabbitTemplate.convertAndSend(QUEUE_NOTA_FISCAL, notaFiscal);
	}

}
