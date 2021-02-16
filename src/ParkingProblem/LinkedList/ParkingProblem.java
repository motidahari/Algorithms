package ParkingProblem.LinkedList;

public class ParkingProblem {
	public static final int x = 0, v = 1;
//	public static int run = 0;
//	public static int run2 = 0;
//	public static int run3 = 0;

	/**
	 * parking problem with linear part and cycle part - not double list
	 * Complexity: O(n)
	 */
	public static int parkingProblemWithLinearPart(LinkedList list) {
		if(list == null || list.getHead() == null) {
			return 0;
		}else if(list.getHead().getNext() == null) {
			return 1;
		}

		Node n = list.getHead().getNext(), m = list.getHead().getNext().getNext();
		while(m != null && m != n) { // check weather there is a cycle
//			run++;
			m = m.getNext();
			if(m != null) m = m.getNext();
			n = n.getNext();
		}
//		System.out.println("while 1 run = " + run);
		if(m == null) {
			return sizeOfList(list);
		}else{
			int count = 0;
			m = list.getHead();
			while(m != n) { // count the length of linear part
//				run2++;
				m = m.getNext();
				n = n.getNext();
				count++;
			}
			m = m.getNext();
			count++;
			while(m != n) { // count the length of cycle
//				run3++;
				m = m.getNext();
				count++;
			}
//			System.out.println("while 3 run = " + run3);
//			System.out.println("total run = " + (run + run2 + run3));
			return count;
		}

	}

	private static int sizeOfList(LinkedList list) {
		int count = 0;
		Node n = list.getHead();
		while(n != null) {
//			run2++;
			n = n.getNext();
			count++;
		}
//		System.out.println("while 2 run = " + run2);
//		System.out.println("total run = " + (run + run2));

		return count;
	}
}
