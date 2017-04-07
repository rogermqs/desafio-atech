package br.com.atech.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;


@Component
public class RabbitListenerAroundAdvice implements MethodInterceptor{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListenerAroundAdvice.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Message message = (Message) invocation.getArguments()[1];
		LOGGER.info("Object in queue "+ new String(message.getBody()));
		Object result = invocation.proceed();
		LOGGER.info("Resuts queue"+ result);
	    return  result;
	}

}
