package MinMaxProblem;

public class MinMax {
	static int max, min, indexMax,indexMin,length;
    static boolean flag = false;


    public static void main(String[] args) {
        int a[] = buildMat.BuildArray.buildRandomArray();//Random Array
        int aInc[] = buildMat.BuildArray.buildSortIncreasingArray();//Increasing Array
        int aDec[] = buildMat.BuildArray.buildSortDecreasingArray();//Decreasing Array
        int [] array = {1,8,9,7,5,6,8,8,5,4,1,3,6,8,4,3,1,8,2,6,99};//array
        MinMax.flag = true;
        System.out.println("\n********** Pairs comparison Random Array **********");
        System.out.println("array size = " + a.length);
        int comp1 = MinMax.minMaxM1(a);
        System.out.println("minMaxM1 => comp1 = " + comp1);
        System.out.println();
        comp1 = MinMax.minMaxM2(a);
        System.out.println("minMaxM2 => comp1 = " + comp1);


        System.out.println("\n********** Pairs comparison Increasing Array **********");
        System.out.println("array size = " + aInc.length);
        comp1 = MinMax.minMaxM1(aInc);
        System.out.println("minMaxM1 => comp2 = " + comp1);
        System.out.println();
        comp1 = MinMax.minMaxM2(aInc);
        System.out.println("minMaxM2 => comp1 = " + comp1);


        System.out.println("\n********** Pairs comparison Decreasing Array **********");
        System.out.println("array size = " + aDec.length);
        comp1 = MinMax.minMaxM1(aDec);
        System.out.println("minMaxM1 => comp3 = " + comp1);
        System.out.println();
        comp1 = MinMax.minMaxM2(aDec);
        System.out.println("minMaxM2 => comp1 = " + comp1);


        System.out.println("\n********** Pairs comparison array **********");
        System.out.println("array size = " + array.length);
        comp1 = MinMax.minMaxM1(array);
        System.out.println("minMaxM1 => comp4 = " + comp1);
        System.out.println();
        comp1 = MinMax.minMaxM2(array);
        System.out.println("minMaxM2 => comp1 = " + comp1);

        System.out.println();
        MinMax.flag = false;
        MinMax.getAverageofMethod(1000,1000);
    }


    /**
     * Find the minimum and the maximum elements in array
     * Complexity: O(n) - 2n comparisons
     *
     * @return number of comparisons
     */
    public static int minMaxM1(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}else if(arr.length == 1){
            printMinMaxComp(0);
            return 0;
        }

        int comparisons = 0;
		//sets the first min and max and indexMax and indexMin
		length = arr.length;
        int max = arr[0], min = arr[0];
		indexMax = indexMin = 0;

        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] > max) {
                max = arr[i];
				indexMax = i;
            }else{
                comparisons++;
                if (arr[i] < min) {
                    min = arr[i];
					indexMin = i;
                }
            }
        }
        if(flag){
            printMinMaxComp(comparisons);
        }
        return comparisons;
    }

    /**
     * Find the minimum and the maximum elements in array
     * Complexity: O(n) - 3/2(n) comparisons
     * @return number of comparisons
     */
    public static int minMaxM2(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }else if(arr.length == 1){
            printMinMaxComp(0);
            return 0;
        }
		//sets the first min and max and indexMax and indexMin
		getFirsMinMaxAndIndex(arr);
		int comparisons = 1;
		length = arr.length;

		for (int i = 3; i < length; i += 2) {
            comparisons++;
            if (arr[i] > arr[i - 1]) {
				comparisons++;
                if (arr[i] > max) {
                    max = arr[i];
                    indexMax = i;
                }
				comparisons++;
                if (arr[i - 1] < min) {
                    min = arr[i - 1];
					indexMin = i - 1;
                }
            }else{
				comparisons++;
                if (arr[i - 1] > max) {
                    max = arr[i - 1];
					indexMax = i - 1;
                }
				comparisons++;
                if (arr[i] < min) {
                    min = arr[i];
					indexMin = i;
                }
            }
        }
		length--;
		// number of array's elements is odd termination
		if (arr.length % 2 != 0) {
			comparisons++;
			if (arr[length] > max) {
				max = arr[length];
				indexMax = length;
			}else{
				comparisons++;
				if (arr[length] < min) {
					min = arr[length];
					indexMin = length;
				}
			}
		}
        if(flag){
            printMinMaxComp(comparisons);
        }
        return comparisons;
    }

	private static void getFirsMinMaxAndIndex(int[] arr) {
		if (arr[0] > arr[1]) {
			max = arr[0];
			min = arr[1];
			indexMax = 0;
			indexMin = 1;
		} else {
			max = arr[1];
			min = arr[0];
			indexMax = 1;
			indexMin = 0;
		}
	}

	private static void printMinMaxComp(int comparisons) {
		System.out.println("[Max = " + max + " | indexMax = " + indexMax + " | Min = " + min +" | indexMin = " + indexMin + " | Comparisons = " + comparisons+"]");
	}

    /**
     *  getAverageofMethod
     * @param checks - number of runs
     * @param max_size - of array
     * @param max_size Prints the average number of comparisons of minMaxM1 and minMaxM2
     */
    public static void getAverageofMethod(int checks, int max_size) {
        int[] arr;
        double sumMethod1 = 0;
        double sumMethod2 = 0;
        for (int i = 0; i < checks; i++) {
            arr = new int[(int) (Math.random() * max_size + 10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * max_size * 10 + 1);
            }
            sumMethod1 += (double) minMaxM1(arr) / arr.length;
            sumMethod2 += (double) minMaxM2(arr) / arr.length;
        }
//        System.out.println("avarage Method1: " +sumMethod1+"/"+checks+" = "+ (sumMethod1/checks)+" with " + checks + " runs"); // => x.yzx....
//        System.out.println("avarage Method2: " +sumMethod2+"/"+checks+" = "+ (sumMethod2/checks)+" with " + checks + " runs"); // => x.yzx....
        System.out.println("avarage Method1: " +sumMethod1+"/"+checks+" = "+ (double)((int)((sumMethod1/checks)*100))/100 +" with " + checks + " runs"); // => x.yz
        System.out.println("avarage Method2: " +sumMethod2+"/"+checks+" = "+ (double)((int)((sumMethod2/checks)*100))/100 +" with " + checks + " runs"); // => x.yz
    }
}
