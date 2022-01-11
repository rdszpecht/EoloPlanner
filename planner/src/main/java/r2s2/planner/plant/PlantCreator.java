package r2s2.planner.plant;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import r2s2.planner.dataConsumers.TopologicalConsumer;
import r2s2.planner.dataConsumers.WeatherConsumer;
import r2s2.planner.queueHandlers.Sender;

@Component
public class PlantCreator {

	@Autowired
	private TopologicalConsumer topologicalConsumer;
	
	@Autowired
	private WeatherConsumer weatherConsumer;
	
	@Autowired
	private Sender sender;
	
	private String plant;
	private int progress;
	
	public void newPlant(String city) {		
		progress = 0;
		
		plant = city;
				
		CompletableFuture<String> topologicalData = topologicalConsumer.getTopologicalData(city);		
		CompletableFuture<Void> topoFuture = topologicalData
				.thenRun(() -> {
					try {
						plant += "-" + topologicalData.get();
						progress += 25;
						sender.sendProgress(progress);
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
						sender.sendProgress(progress);		
					} catch (Exception  e) {						
						e.printStackTrace();
					}				
				});
		
		try {
			topoFuture.get();
			weatherFuture.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		progress += 25;
		sender.sendProgress(progress);
		
		CompletableFuture.allOf(topologicalData, weatherData).thenRun(() -> {
			if (Character.toLowerCase(city.charAt(0)) <= 'm') {
				plant = plant.toLowerCase();
			}else {
				plant = plant.toUpperCase();
			}
			
			sender.sendPlant(plant);
			progress += 25;
			sender.sendProgress(progress);	
		});
		
	}
	
}
