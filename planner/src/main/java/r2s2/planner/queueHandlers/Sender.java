package r2s2.planner.queueHandlers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	RabbitTemplate rabbitTemplate;
	

	public void sendProgress(int progress) {
		rabbitTemplate.convertAndSend("progresses", progress);
	}
	
	public void sendPlant(String plant) {
		rabbitTemplate.convertAndSend("plants", plant);
	}
	
}
