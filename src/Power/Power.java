package Power;

public class Power {

    public static void main(String[] args){
        System.out.println(Math.pow(3,9));
        System.out.println(powerIterative(3,9));
        System.out.println(powerRecursive(3,9));
    }


    /**
     * O(log(n))
     * */
    public static double powerIterative(double x, int n){
        double result = 1;
        while (n > 0){
            if (n % 2 == 1) {
                result = result * x;
            }
            x = x * x;
            n = n/2;
        }
        return result;
    }

    /**
     * O(log(n))
     * */
    public static double powerRecursive(double x, int n){
        if(n == 0) {
            return 1;
        }
        if(n % 2 == 0){
            return powerRecursive(x*x,n/2);
        }
        return x * powerRecursive(x*x,((n-1)/2));
    }



}