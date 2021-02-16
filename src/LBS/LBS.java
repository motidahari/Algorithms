package LBS;

import java.util.Arrays;

public class LBS {

    public static void main(String[] args) {
        int[] arr = new int[]{1,11,2,10,4,5,2,1};
        System.out.println(Arrays.toString(arr));
        int[] lis = lis(arr);
        int[] lds = lds(arr);
        int[] lbs = lbs(arr);
        System.out.println(Arrays.toString(lis) + " | length = " + lis.length);
        System.out.println(Arrays.toString(lds) + " | length = " + lds.length);
        System.out.println(Arrays.toString(lbs) + " | length = " + lbs.length);
    }


    /**
     * get the size of the LBS
     * O(n*log(n))
     * */
    public static int[] lbs(int[] arr) { // only size - O(nlog(n))
        int maxLength = 0;
        int[] result = new int[0];
        for (int i = 1; i < arr.length; i++) {
            int[] arr1 = getSmallArr(arr,0, i+1);
            int[] arr2 = getSmallArr(arr,i+1, arr.length);
            int[] lis = lis(arr1);
            int[] lds = lds(arr2);
            if(maxLength < lis.length + lds.length){
                result = Arrays.copyOf(lis,lis.length + lds.length);
                for (int j = 0; j < lds.length; j++) {
                    result[lis.length + j] = lds[j];
                }
                maxLength = result.length;
            }
        }
        return result;
    }
    public static int[] getSmallArr(int[] arr, int start, int end) { // only size - O(nlog(n))
        int[] array = new int[Math.abs(end - start)];
        for (int i = 0; i < array.length; i++) {
            array[i] = arr[start + i];
        }
        return array;
    }



    /**
     * get the size of the LDS
     * O(n*log(n))
     * */
    public static int[] lds(int[] arr) { // only size - O(nlog(n))
        int[] arr2 = new int[arr.length];
        if(arr.length == 0){
            return new int[0];
        }else if(arr.length == 1){
            return new int[]{arr[0]};
        }

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr2[i] = - arr[i];
        }
        int[] lis = new int[n];
        lis[0] = arr2[0];
        int k = 1;

        for (int i = 1; i < n; i++) {
            int index = Arrays.binarySearch(lis, 0, k, arr2[i]);

            if(index < 0) {
                index = - index - 1; // fix java's results
            }
            if(index == k) {//count the length of the max sequence
                k++;
            }
            lis[index] = arr2[i];//add it to new array
        }

        //get the array
        int[] array = new int[k];
        for (int i = 0; i < array.length; i++) {
            array[i] = - lis[i];
        }
        return array;
    }

    /**
     * get the size of the LIS
     * O(n*log(n))
     * */
    public static int[] lis(int[] arr) { // only size - O(nlog(n))
        if(arr.length == 0){
            return new int[0];
        }else if(arr.length == 1){
            return new int[]{arr[0]};
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
        return array;
    }




}
