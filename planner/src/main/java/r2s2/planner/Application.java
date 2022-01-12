package r2s2.planner;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Queue citiesQueue() {
    	return new Queue("cities", false);
	}
	
	@Bean
	public Queue plantsQueue() {
    	return new Queue("plants", false);
	}
	
	@Bean
	public Queue progressesQueue() {
    	return new Queue("progresses", false);
	}	
}
