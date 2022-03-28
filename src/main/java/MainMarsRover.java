package main.java;

import main.java.model.Request;
import main.java.model.Response;
import main.java.model.Rover;
import main.java.service.IMarsRoverService;
import main.java.service.MarsRoverService;
import java.util.HashMap;
import java.util.Map;

public class MainMarsRover {

	public static void main(String[] args) throws Exception {

		String fileName = "src/main/resources/input";

		IMarsRoverService marsRoverService = new MarsRoverService();
		Request request = marsRoverService.prepareRequest(fileName);
		Response response = marsRoverService.getResponse(request);

		if(response.isValid()){
			HashMap<Integer,String> printMap = preparePrintMap(response);
			for(Integer key : printMap.keySet()){
				System.out.println(printMap.get(key));
			}
		}else{
			System.out.println("response is not valid");
		}
	}

	private static HashMap<Integer,String> preparePrintMap(Response response){
		HashMap<Integer,String> printMap = new HashMap<>();

		for(Rover rover : response.getRovers()){
			if(!response.getExceptionMessageMap().get(rover).equals("")){
				printMap.put(rover.getOrder()-1,(rover.printStatus().concat("-->").concat(response.getExceptionMessageMap().get(rover))));
			} else{
				printMap.put(rover.getOrder()-1,(rover.printStatus()));
			}
		}
		printMap.entrySet()
				.stream()
				.sorted(Map.Entry.<Integer,String>comparingByKey());

		return printMap;
	}
}
