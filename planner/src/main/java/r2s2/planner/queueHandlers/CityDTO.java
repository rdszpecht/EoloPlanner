package r2s2.planner.queueHandlers;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDTO implements Serializable {
	@JsonProperty("id")
	private int id;
	@JsonProperty("city")
	private String city;
	
	public int id() {
		return id;
	}
	public String city() {
		return city;
	}
	

}
