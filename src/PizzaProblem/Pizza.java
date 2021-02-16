package PizzaProblem;

public class Pizza {
    public static int pizza(double x, int n){
        if(x >= n){
            printSlices(n,1,n-1,x);
            return 1;
        }
        int k = (int) (x + 1);
        int p = n/(k+1);
        int r = n % (k+1);
        int ans = -1;
        if(r != 1){
            double t = (x*p + r-1)/((x+1)*p+r);
            if(t < x/(x+1)){
                ans = 1;
            }else{
                ans = 0;
            }
        }
        if(ans != -1){
            countSlicesAndPrint(n,x);
        }
        return ans;
    }
    private static void countSlicesAndPrint(int n,double x) {
        int a = 0;
        double b = 0;
        double count = 0;
        while ((count += (1 + 1*x)) <= n){
            a++;
            b += 1*x;
        }

        if(count != n){
            if(n - count <= 1*x){
               a++;
               b += n - b - a;
            }
        }

        if(a + b != n){
            System.out.println(false);
        }else{
            printSlices(n,a,b,x);
        }

    }

    private static void printSlices(int n, double a, double b,double x) {
        System.out.println("num slices is: "+n+" | and the speed it of b is: "+x+"\n a eat " + a + " slices" + " | b eat " + b + " slices \n");
    }



    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int rand = (int)(Math.random()*100) + 1;
            int rand1 = (int)(Math.random()*100) + 1;
//            pizza(rand,rand1);
            System.out.print(pizza(rand,rand1) + "\n");
        }
    }
}
