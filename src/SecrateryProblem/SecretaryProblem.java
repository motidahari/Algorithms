package SecrateryProblem;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SecretaryProblem {

    public static long getAvarageTime(int[] times) {
        Arrays.sort(times);
        long avg = 0;
        for(int x : times) {
            avg = avg + avg + x;
        }
        return avg/times.length;
    }



    public static void main(String[] args) {
        int[] arr = {10,200,6578,3269,5457,55497,2366,45,0,3,5,4,9};
        System.out.println("getAvarageTime => " + getAvarageTime(arr));
        }


}
