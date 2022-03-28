package main.java.util.state;

import main.java.util.Direction;
import main.java.model.Rover;

public interface IState {

	void left(Rover rover);
	void right(Rover rover);
	void move(Rover rover);
	String printStatus(Rover rover);
	Direction getDirection();
}
