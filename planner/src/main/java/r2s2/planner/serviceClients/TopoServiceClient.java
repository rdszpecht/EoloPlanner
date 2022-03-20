package r2s2.planner.serviceClients;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Value;

import r2s2.planner.plant.CityLandscape;


@Controller
public class TopoServiceClient {
	
	public String getTopographicDetail(String city) {
		String URI = "http://toposervice:8080" + "/api/topographicdetails/";
		
		RestTemplate restTemplate = new RestTemplate();
		String url = URI + city;
		CityLandscape data = restTemplate.getForObject(url, CityLandscape.class);
		
		return data.getLandscape();
	}
		
}
