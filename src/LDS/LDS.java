package LDS;

import java.util.Arrays;

/**
 * O(n^2)
 * */
public class LDS {

    /**
     * get the size of the LDS
     * O(n*log(n))
     * */
    public static int[] lds(int[] arr) { // only size - O(nlog(n))
        if(arr.length == 0){
            return new int[0];
        }else if(arr.length == 1){
            return new int[]{arr[0]};
        }

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = - arr[i];
        }
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
            array[i] = - lis[i];
        }
        return array;
    }

    /**
     * sol 1
     * O(n^2)
     * */

    public static int[] lds2(int[] arr) {
        int n = arr.length;
        int[] lds = new int[n];
        int[][] mat = new int[n][n];
        lds[0] = mat[0][0] = arr[0];
        int k = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearch(mat, k, arr[i]);
            //fix the index
            if(index < 0) {
                index = -index - 1;
            }
            //add value to lis and to the matrix
            lds[index] = mat[index][index] = arr[i];
            //add values to matrix
            for (int j = 0; j < index; j++) {
                mat[index][j] = mat[index-1][j];
            }
            //for the next run
            if(index == k) {
                k++;
            }
        }
//        System.out.println("lds = " +Arrays.toString(lds));
        return Arrays.copyOf(mat[k-1], k);
    }

    /**
     * binarySearch for sol 2
     * */
    private static int binarySearch(int a[][], int end, int key) {
        int left = 0;
        int right = end;
        int middle = 0;
        if (key > a[0][0])
            return 0;
        if (key < a[end][end])
            return end + 1;
        while (left < right - 1) {
            middle = (left + right) / 2;
            if (a[middle][middle] > key) {
                left = middle;
            }
            else {
                right = middle;
            }
        }
        return right;
    }
    /**
     * binarySearch for lds size
     * */
    private static int binarySearchForLds(int a[], int end, int key) {
        int left = 0;
        int right = end;
        int middle = 0;
        if (key > a[0])
            return 0;
        if (key < a[end])
            return end + 1;
        while (left < right - 1) {
            middle = (left + right) / 2;
            if (a[middle] > key) {
                left = middle;
            }
            else {
                right = middle;
            }
        }
        return right;
    }
    public static void main(String[] args) {
        int[] arr = {1,3,2,2,3,4,5,4};
        int[] arr2= {4,5,4,3,2,2,3,1};

        System.out.println("arr = " +Arrays.toString( arr2));
        System.out.println(Arrays.toString(lds(arr2)));
        System.out.println(lds(arr2));
    }
}
