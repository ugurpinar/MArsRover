package main.java.util.state;

import main.java.util.Direction;
import main.java.model.Rover;

public class StateEast implements IState {

	@Override
	public void left(Rover rover) {
		rover.setState(new StateNorth());
	}

	@Override
	public void right(Rover rover) {
		rover.setState(new StateSouth());
	}

	@Override
	public void move(Rover rover) {
		Integer x = rover.getX();
		rover.setX(x+1);
	}

	@Override
	public String printStatus(Rover rover) {
		return rover.getX().toString().concat(" ").concat(rover.getY().toString()).concat(" ").concat(rover.getState().getDirection().name());
	}

	@Override
	public Direction getDirection() {
		return Direction.E;
	}
}
