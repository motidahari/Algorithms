package DonutsProblem;

public class DonutsProblem {
	public static void main(String[] args) {
		System.out.println(getTime(4, 3));
	}

	/**
	 * returns the total time for the daunts
	 * Complexity: O(1)
	 */
	public static int getTime(int numOfDonuts,int capacity) {
		int timeForEachDonuts = 2;
		if(capacity >= numOfDonuts) {
			return timeForEachDonuts;
		}
		if((timeForEachDonuts*numOfDonuts) % capacity == 0) {
			return (timeForEachDonuts * numOfDonuts) / capacity;
		}
		return (timeForEachDonuts * numOfDonuts) / capacity + 1;
	}

}