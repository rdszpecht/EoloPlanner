package r2s2.planner.queueHandlers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import r2s2.planner.dataConsumers.TopologicalConsumer;
import r2s2.planner.dataConsumers.WeatherConsumer;
import r2s2.planner.plant.PlantCreator;
import r2s2.planner.service.EoloPlantService;

@Component
public class Listener {
	
	@Autowired
	private TopologicalConsumer topologicalConsumer;
	
	@Autowired
	private WeatherConsumer weatherConsumer;
	
	@Autowired
	private Sender sender;
	
	@Autowired
	EoloPlantService eoloPlantService;
	
	@RabbitListener(id = "newPlant", queues = "eoloplantCreationRequestsQueue", ackMode = "AUTO")
	public void createEolicPlant(CityDTO message) {
		PlantCreator plantCreator = new PlantCreator(topologicalConsumer, weatherConsumer, eoloPlantService, sender, message.id());
		plantCreator.newPlant(message.city());
		
	}

	
}
