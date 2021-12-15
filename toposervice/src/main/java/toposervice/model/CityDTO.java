package toposervice.model;

public class CityDTO {
	
	private String id;
	private String landscape;
	
	public CityDTO(City city) {
		this.id = city.getName();
		this.landscape = city.getLandscape().getName();
	}

	public String getId() {
		return id;
	}

	public String getLandscape() {
		return landscape;
	}
	
	
}
