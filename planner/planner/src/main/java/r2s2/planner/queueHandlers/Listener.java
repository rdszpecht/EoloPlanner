package r2s2.planner.queueHandlers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

	@RabbitListener(id = "newPlant", queues = "createPlant", ackMode = "AUTO")
	public void createEolicPlant(String message) {
		
		// Crear nueva planta Eolica
	}

	
}
