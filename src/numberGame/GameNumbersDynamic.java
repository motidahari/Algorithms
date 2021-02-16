package numberGame;

import java.util.Arrays;

/**
 * Game Numbers - dynamic algorithm
 * Complexity: O(n^2)
 */
public class GameNumbersDynamic {

    public static void main(String[] args) {
        int[]arr = new int[]{1,3,6,1,3,6};
        n = arr.length;

        System.out.println("n = "+n+" , arr = " + Arrays.toString(arr));
        buildMatrix(arr);

        System.out.println(getOptimalPathRecursive(0,n-1));
        System.out.println(getOptimalPathIterative(0,n-1));

    }
    /**
     * buildMatrix
     * first for O(n)
     * sec for O(n^2)
     * O(n^2) + O(n) = O(n^2) total
     */
    public static void buildMatrix(int[] a) {
        mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            mat[i][i] = a[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                System.out.println("mat["+i+"]["+j+"] = max(a["+i+"] - mat["+(i + 1)+"]["+j+"] = ("+a[i]+" - "+(mat[i + 1][j])+"), a["+j+"] - mat["+i+"]["+(j - 1)+"])= ("+a[j]+ " - "+(mat[i][j-1])+") => "+(Math.max(a[i] - mat[i + 1][j], a[j] - mat[i][j - 1])));
                mat[i][j] = Math.max(a[i] - mat[i + 1][j], a[j] - mat[i][j - 1]);
            }
        }
        for (int i = 0; i < mat.length; i++) {
            System.out.println("row " + i +" =>"  + Arrays.toString(mat[i]));
        }
    }
    private static int[][] mat;
    private static int n;
    private static int run = 0;


    /**
     * Returns the game's optimal path for both players
     */
    public static String getOptimalPathRecursive(int i, int j) {
        return getOptimalPathRec(i, j);
    }
    /**
     * Returns the game's optimal path for both players Recursive
     */
    private static String getOptimalPathRec(int i, int j) {
        if (i == j) {
            System.out.println("if 1 =>  || i = " + i + " , j = " + j);
            return "(i = " + i + ")" + mat[i][i];
        }
        if (mat[i][i] - mat[i + 1][j] > mat[j][j] - mat[i][j - 1]) {
            System.out.println("if 2 =>  i = "+i+ " || mat["+ i +"]["+ i +"] - mat["+(i + 1)+"]["+j+"] = " + (mat[i][i] - mat[i + 1][j] )+ " >  , mat["+j+"]["+j+"] - mat["+i +"]["+(j - 1)+"] = " + (mat[j][j] - mat[i][j - 1]));
            return "(i = " + i + ")" + mat[i][i] + "->" + getOptimalPathRec(i + 1, j);
        } else {
            System.out.println("if 3 =>  j = "+j+ " || mat["+ i +"]["+ i +"] - mat["+(i + 1)+"]["+j+"] = " + (mat[i][i] - mat[i + 1][j] )+ " <=  , mat["+j+"]["+j+"] - mat["+i +"]["+(j - 1)+"] = " + (mat[j][j] - mat[i][j - 1]));
            return "(j = " + j + ")" + mat[j][j] + "->" + getOptimalPathRec(i, j - 1);
        }
    }


    /**
     * Returns the game's optimal path for both players Iterative
     */
    private static String getOptimalPathIterative(int i, int j) {
        String str = "";
        while (true){
            if(i == j) {
//                System.out.println("if 1 =>  || i = " + i + " , j = " + j);
                str += "(i = " + i + ")" + mat[i][i] ;
                break;
            }
            if(mat[i][i] - mat[i + 1][j] > mat[j][j] - mat[i][j - 1]) {
//            System.out.println("if 2 =>  i = "+i+ " || mat["+ i +"]["+ i +"] - mat["+(i + 1)+"]["+j+"] = " + (mat[i][i] - mat[i + 1][j] )+ " >  , mat["+j+"]["+j+"] - mat["+i +"]["+(j - 1)+"] = " + (mat[j][j] - mat[i][j - 1]));
                str += "(i = " + i + ")" + mat[i][i] + "->" ;
                i++;
            }else {
//            System.out.println("if 3 =>  j = "+j+ " || mat["+ i +"]["+ i +"] - mat["+(i + 1)+"]["+j+"] = " + (mat[i][i] - mat[i + 1][j] )+ " <=  , mat["+j+"]["+j+"] - mat["+i +"]["+(j - 1)+"] = " + (mat[j][j] - mat[i][j - 1]));
                str += "(j = " + j + ")" + mat[j][j] + "->" ;
                j--;
            }
        }
        return str;
    }




    /**
     * Returns the difference between the first player and the second player
     * if both playing optimal
     */
    public static int getDifference() {
        return mat[0][mat[0].length - 1];
    }
}
