package ParkingProblem.DoubleLinkedList;

public class ParkingProblem {
	public static final int x = 0, v = 1;


	/**
	 * parking problem with linear part and cycle part - not double list
	 * Complexity: O(n)
	 */
	public static int parkingProblemWithLinearPart(DoubleLinkedList list) {
		if(list == null || list.getHead() == null) return 0;
		if(list.getHead().getNext() == null) return 1;
		NodeDouble n = list.getHead().getNext(), m = list.getHead().getNext().getNext();
		while(m != null && m != n) { // check weather there is a cycle
			m = m.getNext();
			if(m != null) m = m.getNext();
			n = n.getNext();
		}
		if(m == null) return sizeOfList(list);
		int count = 0;
		m = list.getHead();
		while(m != n) { // count the length of linear part
			m = m.getNext();
			n = n.getNext();
			count++;
		}
		m = m.getNext();
		count++;
		while(m != n) { // count the length of cycle
			m = m.getNext();
			count++;
		}
		return count;
	}

	private static int sizeOfList(DoubleLinkedList list) {
		int count = 0;
		NodeDouble n = list.getHead();
		while(n != null) {
			n = n.getNext();
			count++;
		}
		return count;
	}




	/**
	 * parking problem with linear part and cycle part - double linked list
	 * Complexity: O(n^2)
	 */
	public static int parkingProblemWithLinearPart2(DoubleLinkedList list) {
		int result = checkList(list);
		if(result >= 0){
			return  result;
		}
		NodeDouble n = list.getHead();
		NodeDouble m = list.getHead();
		result = 0;

		while(m == n) { //check weather there is a cycle and count the linear part O(n^2)
			result++;
			for(int i = 0; i < result; i++) {
				if (m != null) {
					m = m.getNext();
				}
			}
			for(int i = result; i > 0; i--) {
				if (m != null) {
					m = m.getPrev();
				}
			}
		}
		if(m == null) {
			return result;
		}

		//O(x)
		while(m != n) {//Progress until they reach the same point
			n = n.getNext();
		}
		n = n.getNext();
		result++;
		//O(y)
		while(m != n) { //count the length of cycle by move m
			n = n.getNext();
			result++;
		}
		return result;
	}

	private static int checkList(DoubleLinkedList list) {
		if(list == null || list.getHead() == null) {
			return 0;
		}else if(list.getHead().getNext() == null) {
			return 1;
		}else{
			return -1;
		}
	}


}
