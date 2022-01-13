package r2s2.planner.plant;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import r2s2.planner.dataConsumers.TopologicalConsumer;
import r2s2.planner.dataConsumers.WeatherConsumer;
import r2s2.planner.queueHandlers.PlanningDTO;
import r2s2.planner.queueHandlers.Sender;
import r2s2.planner.service.EoloPlantService;


public class PlantCreator {

	private TopologicalConsumer topologicalConsumer;
	private WeatherConsumer weatherConsumer;
	private EoloPlantService eoloPlantService;
	private Sender sender;
	
	private String plant;
	private int progress;
	private int id;
	
	
	
	public PlantCreator(TopologicalConsumer topologicalConsumer, WeatherConsumer weatherConsumer,
			EoloPlantService eoloPlantService, Sender sender, int id) {
		super();
		this.topologicalConsumer = topologicalConsumer;
		this.weatherConsumer = weatherConsumer;
		this.eoloPlantService = eoloPlantService;
		this.sender = sender;
		this.id = id;
	}



	public void newPlant(String city) {
		
		progress = 0;
		
		plant = city;
				
		CompletableFuture<String> topologicalData = topologicalConsumer.getTopologicalData(city);		
		CompletableFuture<Void> topoFuture = topologicalData
				.thenRun(() -> {
					try {
						plant += "-" + topologicalData.get();
						progress += 25;
						sender.sendProgress(new PlanningDTO(this.id, city, progress, false, null));
					} catch (Exception  e) {						
						e.printStackTrace();
					}
				});
				
		
		CompletableFuture<String> weatherData = weatherConsumer.getWeatherData(city);
		CompletableFuture<Void> weatherFuture = weatherData
				.thenRun(() -> {
					try {
						plant += "-" + weatherData.get();		
						progress += 25;
						sender.sendProgress(new PlanningDTO(this.id, city, progress, false, null));	
					} catch (Exception  e) {						
						e.printStackTrace();
					}				
				});
		
		progress += 25;
		sender.sendProgress(new PlanningDTO(this.id, city, progress, false, null));
		
		CompletableFuture.allOf(topologicalData, weatherData).join();
		
		CompletableFuture<String> calculatedPlant = eoloPlantService.calculatePlant(plant);
		CompletableFuture<Void> plantFuture = calculatedPlant
				.thenRun(() -> {
					try {
						plant = calculatedPlant.get();
						progress += 25;
						sender.sendProgress(new PlanningDTO(this.id, city, progress, true, plant));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
		
	}
	
}
