package ParkingProblem.Array;

public class inArray {
    static int run = 0;

    public static void main(String[] args) {
        int[] arr = {8,8,8,8,8,8,8,8,8};
        int[] arr2 = {1,2,3,4,5,6,7,8,9};
        System.out.println(parkingProblem2(arr));
        System.out.println(parkingProblem2(arr2));
    }

    /**
     * parking problem with array (cycle array)
     * Complexity: O(n)
     */
    public static int parkingProblem(int[] arr) {
        int x = 0, v = 1;
        int count = 0;
        int start = (int)(Math.random() * arr.length);
        arr[start] = v;
        boolean finish = false;
        while(!finish){
//            run++;
            count++;
            if(arr[(start+count)%arr.length] == v) {
                arr[(start+count)%arr.length] = x;
                if(arr[start%arr.length] == x){
                    finish = true;
                }
            }
        }
//        System.out.println("parkingProblem run = " + run);
        return count;
    }

    /**
     * parking problem with array (cycle array)
     * Complexity: O(n)
     */
    public static int parkingProblem2(int[] arr){
        int size = arr.length;
        int start = (int)(Math.random() * arr.length);
        int count = 1;
        int index = start + 1;
        int startElement = arr[start];
        boolean startExists = true;
//        run = 0;
        while (startExists){
//            run++;
            //run if the current value is not equal to the start value
            while (arr[index % size] != startElement){
//                run++;
                count++;
                index++;
            }
            arr[index % size] = startElement + 1;
            if(arr[index % size] == arr[start]){
                startExists = false;
            }
        }
//        System.out.println("parkingProblem2 run = " + run);
        return count;
    }
}
