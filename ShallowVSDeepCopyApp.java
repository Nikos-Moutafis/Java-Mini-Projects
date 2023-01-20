package gr.aueb.cf.Projects;

import java.util.Arrays;

/**
 * This program shows the difference between shallow
 * and deep copy in an array
 */
public class ShallowVSDeepCopyApp {

    public static void main(String[] args) {
        int [][]arr = {{1,2},{3,4},{5,6}};

        System.out.println("original array:");
        printArray(arr);

        printArray(shallowCopy(arr));

        System.out.println("after changing one value in the  shallow copy of original array, the original array changed as well ");
        System.out.println("original  array after changing the shallow copy:");
        printArray(arr);

        System.out.println("deep copy array after changing 100 to 200 in [0,0] position:");
        printArray(deepCopy(arr));


        System.out.println("after changing one value in the  deep copy of original array, the original array did not change");
        System.out.println( "original array after changing the  deep copy: ");
        printArray(arr);

    }

    /**
     * In this method we make a change to copy array
     * and original array changes as well
     *
     * @param arr
     *          the original array
     * @return
     *          the shallow copy of original array
     */
    public static int[][] shallowCopy(int [][]arr){
        int [][]  shallowArr = Arrays.copyOf(arr,arr.length);
        System.out.println("shallow array before change :");
        printArray(shallowArr);
        arr[0][0]=100;
        System.out.println("shallow array after changing 1 to 100 in [0,0] position ");
        return shallowArr;
    }

    /**
     *  In this method we make a change to copy array
     *   and original array does not change
     *
     * @param arr
     * @return
     */
    public static int [][]deepCopy(int[][]arr){
        int [][] deepArr = new int[arr.length][arr[0].length];

        for (int i = 0; i < deepArr.length ; i ++){
            for (int j = 0; j < deepArr[i].length; j ++){
                deepArr[i][j] = arr[i][j];
            }
        }
        deepArr[0][0]=200;

        return  deepArr;
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i <arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
