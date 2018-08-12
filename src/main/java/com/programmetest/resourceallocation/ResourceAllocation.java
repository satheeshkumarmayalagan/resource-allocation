package com.programmetest.resourceallocation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that fits the ball
 *
 */
public class ResourceAllocation {

	public static AllcocationResult allocate(List<Ball> balls, List<Hole> holes) {

		// Sort by descending to fit the bigger sizes
		balls.sort((Ball b1, Ball b2) -> (b2.size - b1.size));
		holes.sort((Hole h1, Hole h2) -> (h2.size - h1.size));

		Iterator<Ball> ballsItr = balls.iterator();
		Iterator<Hole> holesItr = holes.iterator();

		Ball ball = ballsItr.next();
		Hole hole = holesItr.next();

		List<Ball> unAssignedBalls = new ArrayList<Ball>();

		while (true) {
			if (hole.canAllocate(ball)) {
				// Fits. add to the collection of (hole, ball) pairs
				hole.allocate(ball);
				if (!ballsItr.hasNext() || !holesItr.hasNext())
					break; // either no more ball or hole
				ball = ballsItr.next(); // pick the next ball
				hole = holesItr.next(); // pick the next hole
			} else {
				// add to collection of balls that could not be assigned
				unAssignedBalls.add(ball);
				if (!ballsItr.hasNext())
					break; // no more balls to allocate
				ball = ballsItr.next(); // pick the next ball but keep the
										// current hole
			}
		}
		List<Hole> unAssignedHoles = holes.stream().filter(h -> !h.containsBall()).collect(Collectors.toList());
		List<Hole> assignedHolesBalls = holes.stream().filter(h -> h.containsBall()).collect(Collectors.toList());
		return new AllcocationResult(assignedHolesBalls, unAssignedHoles, unAssignedBalls);
	}
}
