package LIS;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LIS {

    /**
     * get the size of the LIS
     * O(n*log(n))
     * */
    public static int getLisSize(int[] arr) { // only size - O(nlog(n))
        if(arr.length == 0){
            return 0;
        }else if(arr.length == 1){
            return 1;
        }

        int n = arr.length;
        int[] lis = new int[n];
        lis[0] = arr[0];
        int k = 1;

        for (int i = 1; i < n; i++) {
            int index = Arrays.binarySearch(lis, 0, k, arr[i]);

            if(index < 0) {
                index = - index - 1; // fix java's results
            }
            if(index == k) {//count the length of the max sequence
                k++;
            }
            lis[index] = arr[i];//add it to new array
        }

        //get the array
        int[] array = new int[k];
        for (int i = 0; i < array.length; i++) {
            array[i] = lis[i];
        }
        System.out.println(Arrays.toString(array));
        return k;
    }


    /**
     * sol 1
     * O(n^2)
     * */
    public static int[] lis(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        int[][] mat = new int[n][n];
        lis[0] = mat[0][0] = arr[0];
        int k = 1;
        for (int i = 1; i < n; i++) {
            int index = Arrays.binarySearch(lis, 0, k, arr[i]);
            //fix the index
            if(index < 0) {
                index = -index - 1;
            }
            //add value to lis and to the matrix
            lis[index] = mat[index][index] = arr[i];
            //add values to matrix
            for (int j = 0; j < index; j++) {
                mat[index][j] = mat[index-1][j];
            }
            //for the next run
            if(index == k) {
                k++;
            }
        }
        System.out.println("lis = " +Arrays.toString(lis));
        return Arrays.copyOf(mat[k-1], k);
    }
    /**
     * sol 2
     * O(n^2)
     * */
    public static int[] LIS(int[] arr){
        int size = arr.length;
        int mat [][] = new int[size][size];
        mat [0][0] = arr[0];
        int currentLength = 0;
        for (int i = 1; i < size; i++){
            int index = binarySearch(mat, currentLength, arr[i]);
            for(int j = 0; j < index; j++){
                mat[index][j] = mat[index-1][j];
            }
            mat[index][index] = arr[i];
            if (index>currentLength){
                currentLength++;
            }
        }
        int[] lis = new int[currentLength+1];
        for(int j = 0; j <= currentLength; j++){
            lis[j]=mat[currentLength][j];
        }
        return lis;
    }
    /**
     * binarySearch for sol 2
     * */
    private static int binarySearch(int a[][], int end, int key) {
        int left = 0;
        int right = end;
        int middle = 0;
        if (key < a[0][0])
            return 0;
        if (key > a[end][end])
            return end + 1;
        while (left < right - 1) {
            middle = (left + right) / 2;
            if (a[middle][middle] < key)
                left = middle;
            else
                right = middle;
        }
        return right;
    }
}
