package MatricsPower;

import java.util.Arrays;

public class PowerOfMatrix {
    public static void main(String[] args){
        int[][] arr ={{1,1},{1,0}};
        System.out.println(Arrays.deepToString(solution(arr,5)));
    }

    /**
     * Solution Iterative
     * O(log n)
     * */
    public static int[][] solution(int[][] n, int k){
        if(k == 0){
            return buildMatrix(n);
        }
        if(k == 1){
            return n;
        }
        int[][] mat = buildMatrix(n);
        while (k > 0) {
            if (k % 2 == 1) mat = matricsMult(mat,n);
            n = matricsMult(n,n);
            k = k/2;
        }
        return mat;
    }

    private static int[][] buildMatrix(int[][] n) {
        int[][] arr = new int[n.length][n.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
        }
        return arr;
    }

    /**
     * Auxiliary function
     * */
    private static int[][] matricsMult(int[][] mat1,int[][] mat2){
        int[][] ans =  new int[mat1.length][mat2[0].length];
        for (int row = 0; row <mat1.length;row++){
            for (int col=0;col<mat2[row].length;col++){
                ans[row][col] = multiplyMatricesCell(mat1, mat2, row, col);
            }
        }
        return ans;
    }

    private static int multiplyMatricesCell(int[][] mat1, int[][] mat2, int row, int col) {
        int cell = 0;
        for (int i = 0; i < mat2.length; i++) {
            cell += mat1[row][i] * mat2[i][col];
        }
        return cell;
    }


}
