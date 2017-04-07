package br.com.atech.config;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JMSConfig {
	
	
	@Autowired
	private AtechErrorHandler atechErrorHandler;
	
	@Autowired
	private RabbitListenerAroundAdvice rabbitListenerAroundAdvice;
	
	@Bean
    public MessageConverter messageConverter() {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        return converter;
    }
	
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
	    RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(messageConverter());
	    return template;
	}
	
	@Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
	
	
	
	@Bean
    public SimpleRabbitListenerContainerFactory containerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setMaxConcurrentConsumers(5);
        container.setMessageConverter(messageConverter());
        container.setAdviceChain(rabbitListenerAroundAdvice);
        container.setErrorHandler(atechErrorHandler);
        return container;
    }
	
	
	@Bean
	public AbstractConnectionFactory connectionFactory() {
	    return new CachingConnectionFactory("localhost");
	}
}
