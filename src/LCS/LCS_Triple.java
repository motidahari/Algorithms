package LCS;

import java.util.Arrays;

public class LCS_Triple {
    /**
     * O(n*m*k)
     */
    public static String LCS(String X, String Y, String Z) {
        int n = X.length();
        int m = Y.length();
        int k = Z.length();

        int[][][] LCS = new int[n + 1][m + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                for (int q = 1; q < k + 1; q++) {
                    if (X.charAt(i - 1) == Y.charAt(j - 1) && X.charAt(i - 1) == Z.charAt(q - 1)) {
                        LCS[i][j][q] = LCS[i - 1][j - 1][q - 1] + 1;
                    } else {
                        LCS[i][j][q] = max(LCS[i - 1][j][q], LCS[i][j - 1][q], LCS[i][j][q - 1]);
                    }
                }
            }
        }
//        System.out.println( Arrays.deepToString(LCS));


        String ans = "";
        int i = n, j = m, q = k;
        // O(n + m + k)
        while (i > 0 && j > 0 && q > 0) {
//            System.out.println("i = " + i + " , j = " + j+ " , q = " + q );
            if (LCS[i][j][q] == LCS[i - 1][j - 1][q - 1] + 1) {
//               System.out.println("LCS["+i+"]["+j+"]["+q+"] = " + LCS[i][j][q] +" == "+ (LCS[i - 1][j - 1][q - 1] + 1) +" = LCS["+(i - 1)+"]["+(j - 1)+"]["+(q - 1)+"] + 1");
                ans = X.charAt(i - 1) + ans;
                i--;
                j--;
                q--;
            } else if (LCS[i][j][q] == LCS[i - 1][j][q]) {
//               System.out.println("LCS["+i+"]["+(j)+"]["+(q)+"] = " + LCS[i][j][q] + " == "+LCS[i - 1][j][q]+" = LCS["+(i - 1)+"]["+j+"]["+q+"]" );
                i--;
            } else if (LCS[i][j][q] == LCS[i][j - 1][q]) {
//                System.out.println("LCS["+i+"]["+(j)+"]["+(q)+"] = " + LCS[i][j][q] + " == "+LCS[i ][j- 1][q]+" = LCS["+(i)+"]["+(j- 1)+"]["+q+"]" );
                j--;
            } else {
                q--;
            }
        }
        return ans;
    }

    public static int max(int x, int y, int z) {
        int max = x;
        if (max < y) {
            max = y;
        }
        if (max < z) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        String X = "sunday";
        String Y = "monday";
        String Z = "may";
        System.out.println(LCS(X, Y, Z));
    }
}
