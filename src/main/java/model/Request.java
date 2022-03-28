package main.java.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

	private PlateauMars plateauMars;
	private Map<Rover, List<String>> rovers;

	public Request() {
		rovers = new HashMap<>();
	}

	public PlateauMars getPlateauMars() {
		return plateauMars;
	}

	public void setPlateauMars(PlateauMars plateauMars) {
		this.plateauMars = plateauMars;
	}

	public Map<Rover, List<String>> getRovers() {
		return rovers;
	}

	public void setRovers(Map<Rover, List<String>> rovers) {
		this.rovers = rovers;
	}
}
