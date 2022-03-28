package main.java.util.state;

import main.java.util.Direction;
import main.java.model.Rover;

public class StateNorth implements IState {

	@Override
	public void left(Rover rover) {
		rover.setState(new StateWest());
	}

	@Override
	public void right(Rover rover) {
		rover.setState(new StateEast());
	}

	@Override
	public void move(Rover rover) {
		Integer y = rover.getY();
		rover.setY(y+1);
	}

	public String printStatus(Rover rover) {
		return rover.getX().toString().concat(" ").concat(rover.getY().toString()).concat(" ").concat(rover.getState().getDirection().name());
	}

	@Override
	public Direction getDirection() {
		return Direction.N;
	}
}
