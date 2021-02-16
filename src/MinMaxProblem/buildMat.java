package MinMaxProblem;

import java.util.ArrayList;
import java.util.Collections;

public class buildMat {
    public static class BuildArray {
        public static int[] buildRandomArray() {
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i = 0; i < 1000000; i++)
                numbers.add(i + 1);

            Collections.shuffle(numbers);

            int[] arr = new int[1000000];
            for (int i = 0; i < 1000000; i++)
                arr[i] = numbers.get(i).intValue();

            return arr;
        }
        //******************************************************
        public static int[] buildSortIncreasingArray() {
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i = 0; i < 1000000; i++)
                numbers.add(i + 1);

            int[] arr = new int[1000000];
            for (int i = 0; i < 1000000; i++)
                arr[i] = numbers.get(i).intValue();
            return arr;
        }

        //******************************************************
        public static int[] buildSortDecreasingArray() {
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i = 0; i < 1000000; i++)
                numbers.add(1000000 - i);

            int[] arr = new int[1000000];
            for (int i = 0; i < 1000000; i++)
                arr[i] = numbers.get(i).intValue();

            return arr;
        }
    }
}
