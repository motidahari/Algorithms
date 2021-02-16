package MinMaxProblem;
import java.util.ArrayList;
import java.util.Stack;

public class MaxMax {
	static int Max, Max2, indexMax,indexMax2,length;
	static boolean flag = false;
	static int comparisons = 0;

	public static void main(String[] args) {
		int a[] = buildMat.BuildArray.buildRandomArray();//Random Array
		int aInc[] = buildMat.BuildArray.buildSortIncreasingArray();//Increasing Array
		int aDec[] = buildMat.BuildArray.buildSortDecreasingArray();//Decreasing Array
		int [] array = {1,8,9,7,5,6,8,8,5,4,1,3,6,8,4,3,1,8,2,6,99};//array

		flag = true;
		System.out.println("\n********** Pairs comparison Random Array **********");
		System.out.println("array size = " + a.length);
		int comp1 = maxMaxM2Recursive(a);
		System.out.println("maxMaxM2Recursive => comp1 = " + comp1);
		System.out.println();
		comp1 = maxMaxM2Induction(a);
		System.out.println("maxMaxM2Induction => comp1 = " + comp1);


		System.out.println("\n********** Pairs comparison Increasing Array **********");
		System.out.println("array size = " + aInc.length);
		comp1 = maxMaxM2Recursive(aInc);
		System.out.println("maxMaxM2Recursive => comp2 = " + comp1);
		System.out.println();
		comp1 = maxMaxM2Induction(aInc);
		System.out.println("maxMaxM2Induction => comp1 = " + comp1);


		System.out.println("\n********** Pairs comparison Decreasing Array **********");
		System.out.println("array size = " + aDec.length);
		comp1 = maxMaxM2Recursive(aDec);
		System.out.println("maxMaxM2Recursive => comp3 = " + comp1);
		System.out.println();
		comp1 = maxMaxM2Induction(aDec);
		System.out.println("maxMaxM2Induction => comp1 = " + comp1);


		System.out.println("\n********** Pairs comparison array **********");
		System.out.println("array size = " + array.length);
		comp1 = maxMaxM2Recursive(array);
		System.out.println("maxMaxM2Recursive => comp4 = " + comp1);
		System.out.println();
		comp1 = maxMaxM2Induction(array);
		System.out.println("maxMaxM2Induction => comp1 = " + comp1);


		System.out.println();
		flag = false;
		getAvarageTimeofMethod(1000, 50);
//		getAvarageTimeofSpecificMethod(1,1000, 50);
//		getAvarageTimeofSpecificMethod(2,1000, 50);
		getAverageofMethod(1,1000, 50);
	}



	/**
	 * Find the 2 maximum elements in array - Recursive
	 * Complexity: O(n) - (n + log n) comparisons
	 * @return number of comparisons
	 */
	public static int maxMaxM2Recursive(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}else if(arr.length == 1){
			Max = arr[0];
			Max2 = arr[0];
			printMinMaxComp(0);
			return 0;
		}
//		System.out.println(arr.toString());
		comparisons = 0;
		Node[] nodes = new Node[arr.length];
		for (int i = 0; i < arr.length; i++) {
			nodes[i] = new Node(arr[i]);
		}

		Node max1 = maxMaxM2Recursive(nodes,0,nodes.length-1);
		int max2 = max1.getStack().pop();
		Max = max1.getData();
		Max2 = max2;

		while(!max1.getStack().isEmpty()) {
			int x = max1.getStack().pop();
			comparisons++;
			if(max2 < x) {
				max2 = x;
				Max2 = x;
			}
		}
		if(flag){
			printMinMaxComp(comparisons);
		}
		return comparisons;
	}
	private static Node maxMaxM2Recursive(Node[] nodes, int low, int high) {
		if(high == low) {
			return nodes[low];
		}
		int mid = (low + high)/2;
		Node maxL = maxMaxM2Recursive(nodes,low,mid);
		Node maxR = maxMaxM2Recursive(nodes,mid+1,high);
		comparisons++;
		if(maxL.getData() > maxR.getData()) {
			maxL.getStack().push(maxR.getData());
			return maxL;
		}else {
			maxR.getStack().push(maxL.getData());
			return maxR;
		}
	}



	public static int maxMaxM2Induction(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}else if(arr.length == 1){
			Max = arr[0];
			Max2 = arr[0];
			printMinMaxComp(0);
			return 0;
		}
		comparisons = 0;
		comparisons++;//from function
		getFirsMinMaxAndIndex(arr);
		for (int i = 2; i < arr.length-1; i += 2) {
			comparisons++;
			if(arr[i+1] > arr[i]) {
				comparisons++;
				if(arr[i+1] > Max) {
					Max2 = Max;
					Max = arr[i+1];
				}
				comparisons++;
				if(arr[i] > Max2) {
					Max2 = arr[i];
				}
			} else {
				comparisons++;
				if(arr[i] > Max) {
					Max2 = Max;
					Max = arr[i];
				}
				comparisons++;
				if(arr[i+1] > Max2) {
					Max2 = arr[i+1];
				}
			}
		}

		if(arr.length % 2 != 0) {
			comparisons++;
			if(arr[arr.length-1] > Max) {
				Max2 = Max;
				Max = arr[arr.length-1];
			}else{
				comparisons++;
				if(arr[arr.length-1] > Max2){
					Max2 = arr[arr.length-1];
				}
			}
		}
		if(flag){
			printMinMaxComp(comparisons);
		}
		return comparisons;
	}



	/**
	 * Find the 2 maximum elements in array - Inductive
	 * Complexity: O(n) - (n + log n) comparisons
	 * @return number of comparisons
	 */
	public static int maxMaxM2Induction2(int[] arr) {
		int comparisons = 0;
		ArrayList<Node> list = new ArrayList<Node>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Node(arr[i]));
		}
		int i = 0;
		while(list.size() > 1) {
			Node x = list.get(i);
			Node y = list.get(i+1);
			comparisons++;
			if(x.getData() > y.getData()) {
				x.getStack().add(y.getData());
				list.remove(i+1);
			}
			else {
				y.getStack().add(x.getData());
				list.remove(i);
			}
			i++;
			if(i == list.size() || i == list.size()-1) i = 0;
		}
		int max1 = list.get(0).getData();
		Stack<Integer> st = list.get(0).getStack();
		int max2 = st.pop();
		while(!st.isEmpty()) {
			int x = st.pop();
			comparisons++;
			if(x > max2) {
				max2 = x;
			}
		}
		if(flag){
			printMinMaxComp(comparisons);
		}
//		System.out.println("Max1 = " + max1 + " , Max2 = " + max2 + " , Comparisons = " + comparisons);
		return comparisons;
	}


	private static void getFirsMinMaxAndIndex(int[] arr) {
		if (arr[0] > arr[1]) {
			Max = arr[0];
			Max2 = arr[1];
			indexMax = 0;
			indexMax2 = 1;
		} else {
			Max = arr[1];
			Max2 = arr[0];
			indexMax = 1;
			indexMax2 = 0;
		}
	}



	/**
	 * @param method
	 * @param checks
	 * @param max_size
	 * Prints the average number of comparisons of given method
	 */

	public static void getAverageofMethod(int method,int checks,int max_size) {
		int[] arr;
		double sum = 0;
		for (int i = 0; i < checks; i++) {
			arr = new int[(int)(Math.random()*max_size+10)];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = (int)(Math.random()*max_size*10+1);
			}
			if(method == 1) sum += (double)maxMaxM2Recursive(arr)/arr.length;
			if(method == 2) sum += (double)maxMaxM2Induction(arr)/arr.length;
		}
		String str = (method == 1)? "maxMaxM2Recursive" : "maxMaxM2Induction";
//		System.out.println("avarage of "+ str +" is :"+ (double)sum/checks + " ms");
		System.out.println("avarage "+str+": " +sum+"/"+checks+" = "+ (double)((int)((sum/checks)*100))/100 +" with " + checks + " runs"); // => x.yz
		System.out.println();
	}
	
	/**
	 * @param checks
	 * @param max_size
	 * Prints the average time of given method
	 */

	public static void getAvarageTimeofSpecificMethod(int method,int checks,int max_size) {
		int[] arr;
		long sum = 0;
		double sumMethod1Recursive = 0;
		double sumMethod2Induction = 0;
		for (int i = 0; i < checks; i++) {
			arr = new int[max_size];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = (int)(Math.random()*max_size*10+1);
			}
			long start = System.currentTimeMillis();
			if(method == 1) maxMaxM2Recursive(arr);
			if(method == 2) maxMaxM2Induction(arr);
			long end = System.currentTimeMillis();
			sum += (end - start);

		}
		String str = (method == 1)? "maxMaxM2Recursive" : "maxMaxM2Induction";
		System.out.println("avarage of "+ str +" is :"+ (double)sum/checks + " ms");
		System.out.println();
	}

	/**
	 * @param checks
	 * @param max_size
	 * Prints the average time of given method
	 */
	public static void getAvarageTimeofMethod(int checks,int max_size) {
		int[] arr;
		long sum1 = 0;
		long sum2 = 0;
		for (int i = 0; i < checks; i++) {
			arr = new int[max_size];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = (int)(Math.random()*max_size*10+1);
			}
			long start = System.currentTimeMillis();
			maxMaxM2Recursive(arr);
			long end = System.currentTimeMillis();
			sum1 += (end - start);
		}

		for (int i = 0; i < checks; i++) {
			arr = new int[max_size];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = (int)(Math.random()*max_size*10+1);
			}
			long start = System.currentTimeMillis();
			maxMaxM2Induction(arr);
			long end = System.currentTimeMillis();
			sum2 += (end - start);
		}
		System.out.println("avarage of maxMaxM2Recursive is :"+ (double)sum1/checks + " ms");
		System.out.println("avarage of maxMaxM2Induction is :"+ (double)sum2/checks + " ms");
		System.out.println();
	}

	private static void printMinMaxComp(int comparisons) {
		System.out.println("[Max = " + Max + " | Max2 = " + Max2 +" |Comparisons = " + comparisons+"]");
	}

	public static int maxMaxM2Induction3(int[]a) {
		int max1 = 0, comparisons = 0;
		for(int i = 1; i < a.length; i++)
		{
			comparisons++;
			if(a[i] > a[max1]) {
				max1 = i;
			}
		}
		System.out.println("the max1 is "+ max1);
		//swap
		int temp;
		temp = a[a.length-1];
		a[a.length-1] = a[max1];
		a[max1] = a[a.length-1];

		int max2 = 0;
		for(int i = 1; i < a.length-1; i++) {
			comparisons++;
			if(a[i] > a[max2]) {
				max2 = i;
			}
		}
		System.out.println("the max2 is "+ max2);
		return comparisons;
	}


}
