package CompilerProblem;

public class CompilerProblem {

    public static void main(String[] args) {
        Program a = new Program("a", 1,2);
        Program b = new Program("b", 2,2);
        Program c = new Program("c", 1,3);
        Program d = new Program("d", 3,1);

        Program[] p = {a,b,c,d};

        getOrder(p);
    }


    public static void getOrder(Program[] programs){
        margeSort(programs, 0, programs.length);
//        for (int i = 0; i < programs.length; i++) {
//            System.out.println(programs[i]);
//        }
        int totaltime = 0;
        int totalen = 0;
        for (int i = 0; i < programs.length; i++) {
            totaltime += (totalen + programs[i].len)*programs[i].freq;
            totalen += programs[i].len;
        }
        System.out.println("total time: " + totaltime);
    }

    public  static  void margeSort(Program[] programs, int low, int high){
        int n = high - low;
        if (n <= 1) {
            return;
        }
        int mid = (low + high)/2;
        margeSort(programs,low,mid);
        margeSort(programs,mid+1,high);
        int i = low, j = mid, k = 0;
        Program[] temp = new Program[n];
        while (i < mid && j < high){
            double t1 = (double)programs[i].len/programs[i].freq;
            double t2 = (double)programs[j].len/programs[j].freq;
            if (t1<t2){
                temp[k++] = programs[i++];
            }else{
                temp[k++] = programs[j++];
            }
        }

        while (i < mid) {
            temp[k++] = programs[i++];
        }
        while (j < high) {
            temp[k++] = programs[j++];
        }
        for (int l = 0; l < n; l++) {
            programs[low+ l] = temp[l];
        }
    }


}

class Program{
    String name;
    int len, freq;

    public Program(String name, int len, int freq){
        this.name = name;
        this.len = len;
        this.freq = freq;
    }

    @Override
    public String toString(){
        return "[" + name + "," + len + "," + freq + "]";
    }
}
