package main.java.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

	private List<Rover> rovers;
	private Map<Rover, String> exceptionMessageMap;
	private boolean isValid;

	public Response() {
		rovers = new ArrayList<>();
		exceptionMessageMap = new HashMap<>();
		isValid = true;
	}

	public List<Rover> getRovers() {
		return rovers;
	}

	public void setRovers(List<Rover> rovers) {
		this.rovers = rovers;
	}

	public Map<Rover, String> getExceptionMessageMap() {
		return exceptionMessageMap;
	}

	public void setExceptionMessageMap(Map<Rover, String> exceptionMessageMap) {
		this.exceptionMessageMap = exceptionMessageMap;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean valid) {
		isValid = valid;
	}
}
