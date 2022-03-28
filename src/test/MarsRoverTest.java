package test;

import main.java.model.PlateauMars;
import main.java.model.Request;
import main.java.model.Response;
import main.java.model.Rover;
import main.java.service.IMarsRoverService;
import main.java.service.MarsRoverService;
import main.java.util.Direction;
import main.java.util.state.StateEast;
import main.java.util.state.StateNorth;
import main.java.util.state.StateSouth;
import main.java.util.state.StateWest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarsRoverTest {


	@Test
	public void givenRoverStateNWithLeft_thenStateW(){
		Rover rover = new Rover();
		rover.setX(3);
		rover.setY(4);
		rover.setState(new StateNorth());

		rover.left();

		String status = rover.printStatus();
		Assert.assertTrue(status.equals("3 4 W"));
	}

	@Test
	public void givenRoverStateWWithRight_thenStateN(){
		Rover rover = new Rover();
		rover.setX(3);
		rover.setY(4);
		rover.setState(new StateWest());

		rover.right();

		String status = rover.printStatus();
		Assert.assertTrue(status.equals("3 4 N"));
	}

	@Test
	public void givenRoverStateEWithMove_thenStateE(){
		Rover rover = new Rover();
		rover.setX(3);
		rover.setY(4);
		rover.setState(new StateEast());

		rover.move();

		String status = rover.printStatus();
		Assert.assertTrue(status.equals("4 4 E"));
	}

	@Test
	public void givenRoverStateNWithAllActions_thenStateW(){
		Rover rover = new Rover();
		rover.setX(1);
		rover.setY(1);
		rover.setState(new StateNorth());

		rover.move();
		rover.move();
		rover.right();
		rover.move();
		rover.left();
		rover.move();
		rover.right();
		rover.move();
		rover.move();
		rover.right();
		rover.move();

		String status = rover.printStatus();
		Assert.assertTrue(status.equals("4 3 S"));
	}

	@Test
	public void givenRequest_thenCorrectState() {
		PlateauMars mars = new PlateauMars();
		mars.setEndX(5);
		mars.setEndY(5);

		Rover rover1 = new Rover();
		rover1.setX(1);
		rover1.setY(1);
		rover1.setOrder(1);
		rover1.setState(new StateNorth());

		Rover rover2 = new Rover();
		rover2.setX(3);
		rover2.setY(4);
		rover2.setOrder(2);
		rover2.setState(new StateSouth());

		Rover rover3 = new Rover();
		rover3.setX(1);
		rover3.setY(1);
		rover3.setOrder(3);
		rover3.setState(new StateEast());


		List<String> instructionList1 = new ArrayList<>(Arrays.asList("L", "M", "R", "M", "M", "L", "M", "R", "M"));
		List<String> instructionList2 = new ArrayList<>(Arrays.asList("M", "R", "R", "E", "M", "R", "M", "R", "R", "M"));
		List<String> instructionList3 = new ArrayList<>(Arrays.asList("L", "M", "M", "R", "M", "L", "M"));

		Request request = new Request();
		request.setPlateauMars(mars);
		request.getRovers().put(rover1,instructionList1);
		request.getRovers().put(rover2,instructionList2);
		request.getRovers().put(rover3,instructionList3);

		IMarsRoverService marsRoverService = new MarsRoverService();
		Response response = marsRoverService.getResponse(request);

		Assert.assertTrue(response.getRovers().size() == 3);
		Assert.assertTrue(response.getExceptionMessageMap().size() == 3);


		Rover responseRover1 = response.getRovers().stream().filter(x->x.getOrder()==1).findFirst().orElse(null);
		Assert.assertTrue(responseRover1.printStatus().equals("-1 4 N"));
		String responseException1 = response.getExceptionMessageMap().get(responseRover1);
		Assert.assertTrue(responseException1.equals("went out of mars"));

		Rover responseRover2 = response.getRovers().stream().filter(x->x.getOrder()==2).findFirst().orElse(null);
		Assert.assertTrue(responseRover2.printStatus().equals("3 4 W"));
		String responseException2 = response.getExceptionMessageMap().get(responseRover2);
		Assert.assertTrue(responseException2.equals("invalid direction"));

		Rover responseRover3 = response.getRovers().stream().filter(x->x.getOrder()==3).findFirst().orElse(null);
		Assert.assertTrue(responseRover3.printStatus().equals("2 4 N"));
		String responseException3 = response.getExceptionMessageMap().get(responseRover3);
		Assert.assertTrue(responseException3.equals(""));


	}
}
