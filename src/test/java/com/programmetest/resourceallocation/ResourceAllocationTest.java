package com.programmetest.resourceallocation;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class ResourceAllocationTest {

	@Property(trials = 10)
	public void testAllocatedAndUnAllocated(List<@InRange(min = "1", max = "100") Integer> holesSizes,
			List<@InRange(min = "1", max = "100") Integer> ballsSizes) {
		// should have atleast one hole and one ball
		assumeThat(holesSizes.size(), greaterThan(0));
		assumeThat(ballsSizes.size(), greaterThan(0));
		assumeThat(holesSizes.size() - ballsSizes.size(), greaterThanOrEqualTo(0));

		List<Hole> holes = new ArrayList<Hole>();
		List<Ball> balls = new ArrayList<Ball>();
		holesSizes.forEach(e -> holes.add(new Hole(e)));
		ballsSizes.forEach(e -> balls.add(new Ball(e)));

		AllcocationResult result = ResourceAllocation.allocate(balls, holes);

		// some of holes with & without balls should be equal to total holes
		assertEquals(holes.size(), result.assignedHolesBalls.size() + result.unAssignedHoles.size());
		// some of allocated & unallocated balls should be equal to total balls
		assertEquals(balls.size(), result.assignedHolesBalls.size() + result.unAssignedBalls.size());
	}

	@Property(trials = 10)
	public void testNoMatch(List<@InRange(min = "1", max = "25") Integer> holesSizes,
			List<@InRange(min = "50", max = "100") Integer> ballsSizes) {
		// smaller hole sizes than the ball sizes...
		assumeThat(holesSizes.size(), greaterThan(0));
		assumeThat(ballsSizes.size(), greaterThan(0));
		assumeThat(holesSizes.size() - ballsSizes.size(), greaterThanOrEqualTo(0));

		List<Hole> holes = new ArrayList<Hole>();
		List<Ball> balls = new ArrayList<Ball>();
		holesSizes.forEach(e -> holes.add(new Hole(e)));
		ballsSizes.forEach(e -> balls.add(new Ball(e)));

		AllcocationResult result = ResourceAllocation.allocate(balls, holes);

		// all remain unassigned
		assertEquals(holes.size(), result.unAssignedHoles.size());
		assertEquals(balls.size(), result.unAssignedBalls.size());
		// No holes with balls
		assertEquals(0, result.assignedHolesBalls.size());
	}

	@Test
	public void testSpecificExample() {

		List<Integer> holesSizes = new ArrayList<Integer>(Arrays.asList(7, 2, 3, 6, 9));
		;
		List<Integer> ballsSizes = new ArrayList<Integer>(Arrays.asList(5, 7, 4, 6));
		;
		List<Hole> holes = new ArrayList<Hole>();
		List<Ball> balls = new ArrayList<Ball>();
		holesSizes.forEach(e -> holes.add(new Hole(e)));
		ballsSizes.forEach(e -> balls.add(new Ball(e)));

		AllcocationResult result = ResourceAllocation.allocate(balls, holes);

		result.assignedHolesBalls
				.forEach(e -> System.out.println("Hole " + e.size + " assigned with ball " + e.ball.size));
		result.unAssignedBalls.forEach(e -> System.out.println("Unassigned Ball " + e.size));
		result.unAssignedHoles.forEach(e -> System.out.println("Unassigned Hole " + e.size));

		// hole size 9 contains ball size 7
		assertEquals(result.assignedHolesBalls.get(0).size, 9);
		assertEquals(result.assignedHolesBalls.get(0).ball.size, 7);
		// hole size 7 contains ball size 6
		assertEquals(result.assignedHolesBalls.get(1).size, 7);
		assertEquals(result.assignedHolesBalls.get(1).ball.size, 6);
		// hole size 6 contains ball size 5
		assertEquals(result.assignedHolesBalls.get(2).size, 6);
		assertEquals(result.assignedHolesBalls.get(2).ball.size, 5);

		// ball 4, the smallest in the example is unassigned
		assertEquals(result.unAssignedBalls.get(0).size, 4);

		// hole size 3, 2 is unassigned
		assertEquals(result.unAssignedHoles.get(0).size, 3);
		assertEquals(result.unAssignedHoles.get(1).size, 2);

	}
}
