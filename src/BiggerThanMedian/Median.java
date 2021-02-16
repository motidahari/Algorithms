package BiggerThanMedian;

import java.util.Arrays;

public class Median {

    public static void main(String [] args){
        int[] arr = {1,5,40,10,20,32,34,100,122,11,313,111,222,333,555,666,888,777,444,555,666,999,888,77,7888,555,123,456,789,789,654,132,564,897,645,213};
        int maxFromMedian = Median(arr);
//        System.out.println(maxFromMedian);

        int numberOfChecks = 100000;
        checkSol(numberOfChecks);
    }


    /**
     * O(1)
     * */
    public static int Median(int[] arr){
        int min = Math.min(64, arr.length/2+1);
        int result = 0;
        for (int i = 0; i < min; i++) {
            if (arr[i] > result ){
                result = arr[i];
            }
        }
        return result ;
    }
    public static void checkSol(int check){
        int countFalse = 0;
        boolean flag = true;
        int rand2,rand,maximum;
        for (int i = 0; i < check; i++) {
            rand = (int)(Math.random())*check + 1;
            int[] arr = new int[rand];
            for (int j = 0; j < arr.length; j++) {
                rand2 = (int)(Math.random()*check) + 1;
                arr[j] = rand2;
            }
            maximum = Median(arr);
            Arrays.sort(arr);
            for (int j = 0; j < arr.length/2+1; j++) {
                if(maximum < arr[j]){
                    flag = false;
                }
            }
            if(!flag){
                countFalse++;
            }
        }
        if(countFalse > 0){
            System.out.println("false => " + (double)((int)((10000/countFalse)*100))/100);
        }else{
            System.out.println("success 100%");
        }
    }
}
