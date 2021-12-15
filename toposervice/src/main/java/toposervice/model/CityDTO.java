package toposervice.model;

public class CityDTO {
	
	private String id;
	private String landScape;
	
	public CityDTO(City city) {
		this.id = city.getName();
		this.landScape = city.getLandscape().getName();
	}

	public String getId() {
		return id;
	}

	public String getLandScape() {
		return landScape;
	}
	
	
}
