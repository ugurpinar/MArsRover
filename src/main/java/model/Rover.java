package main.java.model;

import main.java.util.Direction;
import main.java.util.state.IState;

public class Rover {

	private Integer x;
	private Integer y;
	private Direction direction;
	private IState state;
	private Integer order;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void right() {
		state.right(this);
	}

	public void left() {
		state.left(this);
	}

	public void move() {
		state.move(this);
	}

	public String printStatus() {
		return state.printStatus(this);
	}
}
