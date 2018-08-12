package com.programmetest.resourceallocation;

import java.util.List;
import java.util.Map;

public class AllcocationResult {

	Map<Hole, Ball> assignedHolesBalls;
	List<Hole> unAssignedHoles;
	List<Ball> unAssignedBalls;

	public AllcocationResult(Map<Hole, Ball> assignedHolesBalls, List<Hole> unAssignedHoles,
			List<Ball> unAssignedBalls) {
		this.assignedHolesBalls = assignedHolesBalls;
		this.unAssignedHoles = unAssignedHoles;
		this.unAssignedBalls = unAssignedBalls;
	}

}
