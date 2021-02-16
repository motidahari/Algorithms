package Fibonacci;

public class Fibonacci2 {

    public static void main (String args[]) {
        int n = 9;
        System.out.println(fib(n));
        System.out.println(fibo(n));
        System.out.println(fiboRec(n));
        System.out.println(fib2(n));
    }


    private static final int[][] F = {{1,1},{1,0}};

    /**
     * Solution Iterative
     * returns the nth element in fibonacci series
     * Complexity: O(log n)
     */
    /*
    [Fn+1 , Fn   ]
    [Fn   , Fn-1 ]
    */
    public static int fibo(int n) {
        int[][] ans = F;
        int[][] A = F;
        while(n != 0) {
            if(n % 2 == 1) ans = mulMat(ans,A);
            A = mulMat(A,A);
            n /=2;
        }
        return ans[1][1];//Fn-1
    }

    /**
     * multiple two matrix 2x2
     * Complexity: O(1)
     */
    public static int[][] mulMat(int[][] m1, int[][] m2) {
        int[][] ans = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ans[i][j] = m1[i][0]*m2[0][j] + m1[i][1]*m2[1][j];
            }
        }
        return ans;
    }

    /**
     * Solution Recursive
     * returns the nth element in fibonacci series - recursion
     * Complexity: O(log n)
     */
    public static int fiboRec(int n) {
        return fiboRec(F,n)[1][1];
    }
    public static int[][] fiboRec(int[][] A,int n) {
        if(n == 0) return F;
        if(n % 2 == 1) return mulMat(fiboRec(mulMat(A,A),n/2),A);
        return fiboRec(mulMat(A,A),n/2);
    }



/**
 * fib rec
 **/
    public static int fib(int n) {
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    /**
     * fib iter
     **/
    public static int fib2(int n) {
        int [] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[arr.length-1];
    }
}
