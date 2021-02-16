package SubMatrix;

import java.util.Arrays;
import java.util.Vector;

public class SubMatrix_nXn {
	
	/**
	 * returns the biggest square contains ones
	 * Complexity: O(n*m)
	 */
	public static int getBiggestSubMatrix(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		int[][] help = new int[row][col];
		int max = 0,rowMax = 0,colMax = 0;

		/**
		 * init rows in the same value from the original mat
		 * init cols in the same value from the original mat
		 * */
		for (int i = 0; i < row; i++) {
			if(i < row){
				help[i][0] = mat[i][0];
			}
			if(i < col){
				help[0][i] = mat[0][i];
			}
		}
//		System.out.println(Arrays.deepToString(help));
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if(mat[i][j] == 1) {
					int left = help[i][j-1];
					int up = help[i-1][j];
					int diag = help[i-1][j-1];
					help[i][j] = min(left,up,diag) + 1;//check the min index
					if(help[i][j] > max) {
						max = help[i][j];
						colMax = j - max + 1;
						rowMax = i - max + 1;
					}
				}
			}
		}
		if(max != 0)System.out.println("Max square length is: " + max + ", start at: (" + rowMax + "," + colMax +") end at (" + (rowMax + max - 1) + "," + (colMax + max - 1) +")");
		return max;
	}

	private static int min(int i, int j, int k) {
		if(i <= j && i <= k) return i;
		if(j <= i && j <= k) return j;
		if(k <= i && k <= j) return k;
		else return -1;
	}

	/**
	 * returns all biggest squares contains ones
	 * Complexity: O(n*m)
	 */
	public static Vector<String> getAllMaxSubSquares(int[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		int[][] help = new int[n][m];
		int max = 0;
		for (int i = 0; i < n; i++) {
			help[i][0] = mat[i][0];
		}
		for (int i = 0; i < m; i++) {
			help[0][i] = mat[0][i];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if(mat[i][j] == 1) {
					help[i][j] = min(help[i][j-1],help[i-1][j],help[i-1][j-1]) + 1; 
					if(help[i][j] > max) {
						max = help[i][j];
					}
				}
			}
		}
		Vector<String> allSquares = new Vector<String>();
		if(max == 0) return allSquares;
		for (int i = 0; i < help.length; i++) {
			for (int j = 0; j < help[0].length; j++) {
				if(help[i][j] == max) {
					allSquares.add("(" + (i-max+1) + "," + (j-max+1) +") - (" + (i) + "," + (j) +  ") -> size =  " + max + "x" + max + " | ");
				}
			}
		}
		return allSquares;
	}

	public static void main(String[] args) {
		int [][] arr = {
				{0,1,1,0},
				{0,1,1,1},
				{0,1,1,1},
				{0,1,1,1},
				{1,1,1,1}};
		getBiggestSubMatrix(arr);
		Vector<String> v = getAllMaxSubSquares(arr);
		System.out.println(v.toString());
	}
}