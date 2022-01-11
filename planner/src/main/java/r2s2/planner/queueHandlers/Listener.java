package r2s2.planner.queueHandlers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import r2s2.planner.plant.PlantCreator;

@Component
public class Listener {

	@Autowired
	private PlantCreator plantCreator;
	
	@RabbitListener(id = "newPlant", queues = "createPlant", ackMode = "AUTO")
	public void createEolicPlant(String message) {
		plantCreator.newPlant(message);
		
	}

	
}
