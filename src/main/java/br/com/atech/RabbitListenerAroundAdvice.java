package br.com.atech;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;


@Component
public class RabbitListenerAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Message message = (Message) invocation.getArguments()[1];
		System.out.println("before->"+new String(message.getBody()));
		try{
			Object result = invocation.proceed();
			System.out.println("after->"+new String(message.getBody()));
	        return  result;
		}catch (RuntimeException e) {
			System.out.println("Error--->>"+e.getMessage());
		}
		return null;
	}

}
