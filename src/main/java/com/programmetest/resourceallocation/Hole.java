package com.programmetest.resourceallocation;

public class Hole {
	int size; // default visibility for better readablity of this example
	Ball ball;

	public Hole(int size) {
		this.size = size;
	}

	public boolean canAllocate(Ball ball) {
		return ball.size <= this.size;
	}

	public void allocate(Ball ball) {
		this.ball = ball;
	}

	public boolean containsBall() {
		return this.ball != null;
	}

}
