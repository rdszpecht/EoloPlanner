package r2s2.planner.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EoloPlantService {
	
	@Async
	public CompletableFuture<String> calculatePlant(String rawPlant) {
		String plant = rawPlant;
		String city = rawPlant.split("-")[0];
		
		if (Character.toLowerCase(city.charAt(0)) <= 'm') {
			plant = plant.toLowerCase();
		}else {
			plant = plant.toUpperCase();
		}
		
		try {
			Thread.sleep((long) (Math.random() * 2000 + 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return CompletableFuture.completedFuture(plant);
	}
}
