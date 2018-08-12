package com.programmetest.resourceallocation;

import java.util.List;

public class AllcocationResult {

	List<Hole> assignedHolesBalls;
	List<Hole> unAssignedHoles;
	List<Ball> unAssignedBalls;

	public AllcocationResult(List<Hole> assignedHolesBalls, List<Hole> unAssignedHoles, List<Ball> unAssignedBalls) {
		this.assignedHolesBalls = assignedHolesBalls;
		this.unAssignedHoles = unAssignedHoles;
		this.unAssignedBalls = unAssignedBalls;
	}

}
