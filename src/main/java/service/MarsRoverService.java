package main.java.service;

import main.java.util.Direction;
import main.java.util.Insctruction;
import main.java.model.PlateauMars;
import main.java.model.Request;
import main.java.model.Response;
import main.java.model.Rover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarsRoverService implements IMarsRoverService {

	@Override
	public Request prepareRequest(String fileName) throws Exception {
		Request request = new Request();
		try(BufferedReader reader = new BufferedReader(
				new FileReader(fileName))){

			String line;
			int lineCount = 0;
			int order = 0;
			while((line = reader.readLine()) != null){
				lineCount++;
				if(lineCount == 1){
					String[] plateauMarsArray = line.split(" ");
					if(plateauMarsArray.length >= 2){
						PlateauMars mars = new PlateauMars();
						mars.setEndX(Integer.valueOf(plateauMarsArray[0]));
						mars.setEndY(Integer.valueOf(plateauMarsArray[1]));
						request.setPlateauMars(mars);
					} else{
						throw new Exception("failed to create request , input file is inappropriate");
					}
				} else{
					if(lineCount % 2 == 0){
						String[] roverArray = line.split(" ");
						if(roverArray.length == 3){
							Rover rover = new Rover();
							order++;
							rover.setX(Integer.valueOf(roverArray[0]));
							rover.setY(Integer.valueOf(roverArray[1]));
							rover.setDirection(Direction.valueOf(roverArray[2]));
							rover.setState(rover.getDirection().getState());
							rover.setOrder(order);
							line = reader.readLine();
							lineCount++;
							List<String> instructionList = new ArrayList<>();
							for(char ch : line.toCharArray()){
								String instruction = String.valueOf(ch);
								instructionList.add(instruction);
							}
							request.getRovers().put(rover, instructionList);
						} else{
							throw new Exception("failed to create request , input file is inappropriate");
						}
					}
				}
			}

			return request;
		} catch(IOException e){
			throw e;
		} catch(NumberFormatException e){
			throw new Exception("failed to create request , input file is inappropriate");
		} catch(Exception e){
			throw e;
		}
	}

	@Override
	public Response getResponse(Request request) {
		Response response = new Response();

		try{
			boolean isRequestValid = request.getRovers() != null && request.getRovers().keySet().size() > 0;
			if(isRequestValid){
				for(Rover rover : request.getRovers().keySet()){
					Map roverExceptionMap = new HashMap();
					roverExceptionMap.put(rover, "");
					response.getExceptionMessageMap().put(rover, "");

					List<String> instructionList = request.getRovers().get(rover);
					for(String key : instructionList){
						Insctruction insctruction = null;
						try{
							insctruction = Insctruction.valueOf(key);
						} catch(Exception e){
							response.getExceptionMessageMap().remove(rover);
							response.getExceptionMessageMap().put(rover, "invalid direction");
						}
						try{
							if(insctruction != null){
								if(insctruction.equals(Insctruction.L)){
									rover.left();
								} else if(insctruction.equals(Insctruction.R)){
									rover.right();
								} else if(insctruction.equals(Insctruction.M)){
									rover.move();
								} else{
									response.getExceptionMessageMap().remove(rover);
									response.getExceptionMessageMap().put(rover, "invalid direction");
								}
							}
						}catch(Exception e){
							response.setValid(false);
						}
					}

					response.getRovers().add(rover);
					if(!isPositionValid(request.getPlateauMars(), rover)){
						response.getExceptionMessageMap().remove(rover);
						response.getExceptionMessageMap().put(rover, "went out of mars");
					}
				}
			} else{
				response.setValid(false);
			}
		} catch(Exception e){
			response.setValid(false);
		}
		return response;
	}

	private boolean isPositionValid(PlateauMars plateauMars, Rover rover) {
		return plateauMars.getStartX() <= rover.getX()
				&& plateauMars.getStartY() <= rover.getY()
				&& plateauMars.getEndX() >= rover.getX()
				&& plateauMars.getEndY() >= rover.getY();
	}
}
