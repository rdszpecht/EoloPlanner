package r2s2.planner.serviceClients;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.node.ObjectNode;

import r2s2.planner.plant.CityLandscape;


@Controller
public class TopoServiceClient {
	
	private final String URI = "http://localhost:8080/api/topographicdetails/";
	
	public String getTopographicDetail(String city) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = URI + city;
		CityLandscape data = restTemplate.getForObject(url, CityLandscape.class);
		
		return data.getLandscape();
	}
		
}
