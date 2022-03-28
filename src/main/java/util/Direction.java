package main.java.util;

import main.java.util.state.IState;
import main.java.util.state.StateEast;
import main.java.util.state.StateNorth;
import main.java.util.state.StateSouth;
import main.java.util.state.StateWest;

public enum Direction {
	S(new StateSouth()),
	N(new StateNorth()),
	E(new StateEast()),
	W(new StateWest());

	private IState state;

	Direction(IState state) {
		this.state = state;
	}

	public IState getState() {
		return state;
	}
}
