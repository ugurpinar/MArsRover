package main.java.service;

import main.java.model.Request;
import main.java.model.Response;

public interface IMarsRoverService {

	Request prepareRequest(String fileName) throws Exception;
	Response getResponse(Request request);
}
