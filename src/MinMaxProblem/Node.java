package MinMaxProblem;

import java.util.Stack;

/**
 * Node with data and stack
 */
public class Node {
	private int data;
	private Stack<Integer> st;
	
	public Node(int data) {
		this.data = data;
		st = new Stack<Integer>();
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Stack<Integer> getStack() {
		return st;
	}

	public void setStack(Stack<Integer> st) {
		this.st = st;
	}

	@Override
	public String toString() {
		return "#"+data+" -> {" +st.toString() + "}";
	}
}
