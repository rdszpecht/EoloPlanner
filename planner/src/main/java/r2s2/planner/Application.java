package r2s2.planner;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}


	@Bean
	public Queue eoloplantCreationRequestsQueue() {
    	return new Queue("eoloplantCreationRequests", false);
	}
	
	@Bean
	public Queue eoloplantCreationProgressNotificationsQueue() {
    	return new Queue("eoloplantCreationProgressNotifications", false);
	}

}
