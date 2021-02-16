package SubMatrix;

import java.util.Stack;
import java.util.Vector;

public class SubMatrix_nXm {
	public static void main(String[] args) {
		int [][] arr = {
				{0,1,1,1},
				{0,1,1,1},
				{0,1,1,1},
				{0,1,1,1},
				{1,1,1,1}};
		System.out.println(findMaxRectangle(arr));

	}
	/**
	 * returns the biggest square contains ones
	 * Complexity: O(n*m)
	 */
	public static int findMaxRectangle(int[][] mat) {
		if(mat.length == 0 || mat[0].length == 0){
			return 0;
		}
		int row = mat.length;
		int col = mat[0].length;
		int[] help = new int[col];
		//init all value in array to 0
		for (int i = 0; i < help.length; i++) {
			help[i] = 0;
		}
		int max = 0;
//		System.out.println(Arrays.deepToString(help));
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(mat[i][j] == 1) {
					help[j] = help[j] + 1;
				}else{
					help[j] = 0;
				}
			}
			int area = getMaxArea(help);
			if(area > max){
				max = area;
			}
		}
		return max;
	}

	private static int getMaxArea(int[] help) {
		int i,tp,areaWithTop,maxArea,n = help.length;
		i = tp = areaWithTop = maxArea = 0;
		Stack<Integer> st = new Stack<>();

		while (i < n){
			if(st.isEmpty() || help[st.peek()] <= help[i]){
				st.push(i++);
			}else{
				tp = st.pop();
				int x = (st.isEmpty()) ? i : i - st.peek()-1;
				areaWithTop = help[tp] * x;
			}
			if(maxArea < areaWithTop){
				maxArea = areaWithTop;
			}
		}
		while(!st.isEmpty()){
			tp = st.pop();
			int x = (st.isEmpty()) ? i : i - st.peek()-1;
			areaWithTop = help[tp] * x;
			if(maxArea < areaWithTop){
				maxArea = areaWithTop;
			}
		}
		return maxArea;
	}
	
}