package toposervice.model;

import org.springframework.data.annotation.Id;

public class City {
	
	@Id
	private String id;
	private String name;
	
	private Landscape landscape;
	private String landscapeId;
	
	public City() {}
	
	public City(String name, Landscape landscape) {
		this.name = name;
		this.landscapeId = landscape.getId();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLandscapeId() {
		return this.landscapeId;
	}
	
	public Landscape getLandscape() {
		return this.landscape;
	}
	
	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}

}
