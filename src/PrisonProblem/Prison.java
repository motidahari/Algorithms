package PrisonProblem;

import java.util.Arrays;

public class Prison {
	private static final int NumOfPrisoners = 5; // 0...9
	private static final int counterMan = 0;

	/**
	 * prison problem when the initial state of light is known (on)
	 * Complexity: O(?)
	 */
	public static void prisonProblem() {
		boolean light = true;
		boolean firstTime = true;
		boolean[] enter = new boolean[NumOfPrisoners];
		//System.out.println(Arrays.toString(enter));
		int count = 1;
		enter[counterMan] = true;
		int steps = 0;
		while(count < NumOfPrisoners) {
			steps++;
			int turnToEnter = (int)(Math.random()*NumOfPrisoners);
			//the counting man in the first time
			if(turnToEnter == counterMan && !light) {
					light = true;
					count++;
			}
			//the other normal prisons
			else {
				//checks if a "regular" prisoner entered the room with open light in the first time
				if(light && !enter[turnToEnter]) {
					light = false;
					enter[turnToEnter] = true;
				}
			}
		}
		for (int i = 0; i < enter.length; i++) {
			if(!enter[i]) {
				System.out.println("Fail!");
				return;
			}
		}
		System.out.println("We are free!");
		System.out.println("Number of steps = " + steps);
	}
	public static void prisonProblem2() {
		boolean light = true;
		boolean firstTime = true;
		boolean[] enter = new boolean[NumOfPrisoners];
		System.out.println(Arrays.toString(enter));
		int count = 0;
		int steps = 0;
		while(count < NumOfPrisoners) {
			steps++;
			int turnToEnter = (int)(Math.random()*NumOfPrisoners);
			//the counting man in the first time
			if(turnToEnter == counterMan) {
				if(firstTime) {
					enter[counterMan] = true;
					count++;
					firstTime = false;
				}
				if(!light) {
					light = true;
					count++;
				}
			}
			//the other normal prisons
			else {
				//checks if a "regular" prisoner entered the room with open light in the first time
				if(light && !enter[turnToEnter]) {
					light = false;
					enter[turnToEnter] = true;
				}
			}
		}
		for (int i = 0; i < enter.length; i++) {
			if(!enter[i]) {
				System.out.println("Fail!");
				return;
			}
		}
		System.out.println("We are free!");
		System.out.println("Number of steps = " + steps);
	}
	
	/**
	 * prison problem when the initial state of light is Unknown
	 */
	public static void prisonProblemUnknownState() {
		boolean light = (int)(Math.random()*2) == 0 ? false : true;
//		boolean firstTime = true;
		int[] enter = new int[NumOfPrisoners];
		int count = 2;
		int steps = 0;
		while(count < 2 * NumOfPrisoners) {
			steps++;
			int turnToEnter = (int)(Math.random()*NumOfPrisoners);
			if(turnToEnter == counterMan && light) {
				enter[counterMan]++;
				count++;
				light = false;
			}else if(!light && enter[turnToEnter] < 2) {
					light = true;
					enter[turnToEnter]++;
			}
		}
		for (int i = 0; i < enter.length; i++) {
			if(enter[i] == 0) {
				System.out.println("Fail!");
				return ;
			}
		}
		System.out.println("We are free!");
		System.out.println("Number of steps = " + steps);
	}
	
	public static void main(String[] args) {

		prisonProblemUnknownState();
		prisonProblem();
	}
}