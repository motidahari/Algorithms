package LCS;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LCS {

    /**
     * O(n*m)
     * */
    public static String lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int [][] arr = new int[n+1][m+1];

        for (int i = 1; i < n + 1; i++) { // (n*m)
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }

        int i = n;
        int j = m;
        String result = "";
        //O(n+m)
        while (arr[i][j] != 0){
            System.out.println("i = " + i + " , j = " + j );
            if(s1.charAt(i-1) == s2.charAt(j-1)){
//                System.out.println("s1.charAt("+(i - 1)+") = " + s1.charAt(i-1) + " == " + s2.charAt(j-1) + " = s2.charAt("+(j-1)+") ");
                result = s1.charAt(i-1) + result;
                i--;
                j--;
            }else {
                if ((arr[i][j - 1] > arr[i - 1][j])) {
//                    System.out.println("arr["+i+"]["+(j - 1)+"] = " + arr[i][j - 1] + " > "+arr[i - 1][j]+" = arr["+(i - 1)+"]["+j+"]" );
                    j--;
                }else {
//                    System.out.println("arr["+i+"]["+(j - 1)+"] = " + arr[i][j - 1] + " <= "+arr[i - 1][j]+" = arr["+(i - 1)+"]["+j+"]" );
                    i--;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String s1 = "bdcaba";
        String s2 = "abcbdab";
        String result = lcs(s2,s1);
        System.out.println(result);
    }
}
