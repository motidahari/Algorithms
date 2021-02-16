package GlassBall;

import java.util.Arrays;

public class GlassBall4 {

	public static void main(String[] args) {
		boolean [] arr = new boolean[100] ;
		int f = (int)(Math.random() * arr.length);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (f <= i )? true: false;
		}
		System.out.println(glassBallWithSqrt(arr,f) + " == " +f + " = floor" );
		System.out.println(glassBallWithTriangular(arr,f) + " == " +f + " = floor" );

		int[] a= {1,2,3,4,5,6,7,8,9,10};
		System.out.println(Arrays.toString(a));
//		System.out.println(numberOfCheckingK(115,2));
		useFloorPotenial(8,a);
		System.out.println(numberOfCheckingK(a.length,2));


	}
	static int run = 0;
	/**
	 * Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into equal parts
	 * Complexity: O(sqrt(n)) - 2*sqrt(n)
	 */
	public static int glassBallWithSqrt(boolean[] floors, int ball) {
		int n = floors.length;
		int step = (int) Math.sqrt(n);
		int currentFloor = step;
		boolean isBreak = false;
		run = 0;
		while(!isBreak) {
			run++;
			//if the ball broke
			if(floors[currentFloor]) {
				currentFloor = currentFloor - step ;
				while(!isBreak) {
					run++;
					if(floors[currentFloor]) {
						System.out.println("run = " + run);
						return currentFloor;
					}
					currentFloor++;
				}
			}
			//if the ball isn't broke in all floors
			if(currentFloor == n-1) {
				break;
			}
			currentFloor += step;
			//if the floor bigger than the building
			if(currentFloor > n-1) {
				currentFloor = n - 1;
			}
		}
		System.out.println("number of checks = " + run);
		return n;
	}



	
	/**
	 * Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into different parts
	 * Complexity: O(sqrt(n)) - sqrt(2*n)
	 */
	public static int glassBallWithTriangular(boolean[] floors, int ball) {
		int n = floors.length;
		int step = 1;
		//get k for running
		while((step * (step+1)) / 2 < n) {
			step++;
		}
		run = 0;
		int currentFloor = step;//start floor
		boolean isBreak = false;
		while(!isBreak) {
			run++;
			//if the ball broke
			if(floors[currentFloor]){
				currentFloor = currentFloor - step ;
				while(!isBreak) {
					if(floors[currentFloor]) {
						return currentFloor;
					}
					currentFloor++;
				}
			}
			//if the ball isn't broke in all floors
			if(currentFloor == n-1) {
				break;
			}
			step--;
			currentFloor += step;
			//if the floor bigger than the building
			if(currentFloor > n-1) {
				currentFloor = n - 1;
			}
		}
		System.out.println("number of checks = " + run);

		return n;
	}

	/**
	 * Worst Case sqrt 2n
	 */
	public static void useFloorPotenial(int brakeFloor,int arr[]) {
		int num = 1;
		int numFloor = arr.length;
		while(numFloor > num * (num+1)/2) {
			num++;
		}
		int jump = num;
		int step = num-1;
		run = 0;
		while (arr[jump] <= brakeFloor) {
			run++;
//			System.out.println("jump = " + jump + " + " + step + " = " + (jump + step) );
			jump += step;
			step--;

		}
		System.out.println("the first ball break "+jump);
		int Floor = jump - (step+1);
//		System.out.println("Floor = " + jump + " - " + (step+1) + " = " + (jump - (step+1)) );
		while(arr[Floor] <= brakeFloor) {
			run++;
			Floor++;
		}
		System.out.println("the Seconde Ball is Broking "+ Floor);
//		System.out.println("number of checks = " + run);
	}

	/**
	 * Worst Case O(n^2*k)
	 */
	public static int numberOfCheckingK(int floors,int balls) {
		int numCheack = 0, min = 0;
		int[][] cheack = new int[balls+1][floors+1];
		for (int j = 0; j <= floors; j++) {
			cheack[0][j] = 0;
			cheack[1][j] = j;
		}
		for (int i = 2; i <= balls; i++) {
			for (int j = 2; j <=floors; j++) {
				min = floors+1;
				for (int p = 1; p < j; p++) {
					min = Math.min(Math.max(cheack[i][j-p], (cheack[i-1][p-1]))+1, min);
				}
				cheack[i][j] = min;
			}
		}
		numCheack =	cheack[balls][floors];
		return numCheack;
	}


	/**
	 * Worst Case O(n^2)
	 */
	public static int numberofcheacking2(int n) {
		int[] f = new int[n+1];
		f[0] = 0;
		f[1] = 1;
		f[2] = 2;
		for (int i = 3; i <= n; i++) {
			int min = n;
			for (int j = 1; j < i-1; j++) {
				int x = Math.max(j, f[i-j]+1);
				if (x < min) min = x;
			}
			f[i] = min;
		}
		return f[n];
	}
}