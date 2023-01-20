package gr.aueb.cf.Projects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program reads numbers from a file
 * gets the combination of six numbers
 * and then after it  checks some conditions
 * prints the combinations into another file.
 */

public class CombinationsOfSixApp {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("/C:/Users/nikos/Desktop/numbers.txt"));
             PrintStream ps = new PrintStream("/C:/Users/nikos/Desktop/combinations.txt")){
                getNumbers(in, ps);
            }catch (IOException e) {
            System.out.println("please check your file and file path");
            }
    }

    /**
     * This method reads numbers from a file
     * calculates the count of them and then creates a new array
     * based on that count, after takes all possible combinations of
     * six numbers, after it checks them with five conditions.
     * If the combination of six numbers is valid, it prints them
     * into a file.
     * @throws FileNotFoundException
     */
    public static void getNumbers(Scanner in, PrintStream ps) throws FileNotFoundException {
        int[] arr = new int[6];
        final int N = 6;
        int count  = 0;


        while (in.hasNextInt()){
            count ++ ;
            in.nextInt();
        }
        in.close();

        int []arr1 =new int[count];
        Scanner into = new Scanner(new File("/C:/Users/nikos/Desktop/numbers.txt"));
        for (int i = 0; i < arr1.length; i++){
            arr1[i] = into.nextInt();
        }

        Arrays.sort(arr1);


        for (int i = 0; i <= arr1.length - N; i++) {
            for (int j = i + 1; j <= arr1.length - N + 1; j++) {
                for (int k = j + 1; k <= arr1.length - N + 2; k++) {
                    for (int m = k + 1; m <= arr1.length - N + 3; m++) {
                        for (int l = m + 1; l <= arr1.length - N + 4; l++){
                            for (int n = l + 1; n < arr1.length; n++) {
                                arr[0] = arr1[i];
                                arr[1] = arr1[j];
                                arr[2] = arr1[k];
                                arr[3] = arr1[m];
                                arr[4] = arr1[l];
                                arr[5] = arr1[n];

                                if (!isEven(arr) && !isOdd(arr) && !isContiguous(arr) && !isSameEnding(arr) &&!isSameTen(arr)){
                                    ps.println(Arrays.toString(arr));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method counts the even numbers in a given array
     * gives true if there are more than 4 even
     * false otherwise
     *
     * @param arr
     *            the arr with the numbers
     * @return
     *          true if there are more than 4 even numbers,false otherwise
     */
    public static boolean isEven(int [] arr){
        int evenCount = 0;
        if (arr[0] % 2 ==0) evenCount ++;
        if (arr[1] % 2 ==0) evenCount ++;
        if (arr[2] % 2 ==0) evenCount ++;
        if (arr[3] % 2 ==0) evenCount ++;
        if (arr[4] % 2 ==0) evenCount ++;
        if (arr[5] % 2 ==0) evenCount ++;

        return evenCount > 4;
    }

    /**
     * This method counts the odd numbers in a given array
     * gives true if there are more than 4 odd
     * false otherwise
     *
     * @param arr
     *            the arr with the numbers
     * @return
     *          true if there are more than 4 odd numbers,false otherwise
     */

    public static boolean isOdd(int [] arr){
        int oddCount = 0;
        if (arr[0] % 2 == 1) oddCount ++;
        if (arr[1] % 2 == 1) oddCount ++;
        if (arr[2] % 2 == 1) oddCount ++;
        if (arr[3] % 2 == 1) oddCount ++;
        if (arr[4] % 2 == 1) oddCount ++;
        if (arr[5] % 2 == 1) oddCount ++;

        return oddCount > 4;
    }


    /**
     * This method counts if there are more than two contiguous numbers
     * in  an array and returns true if there are more than 2 contiguous
     * @param arr
     *            the array with the numbers
     * @return
     *         true if is there are more than two contiguous numbers, false otherwise
     */
    public static boolean isContiguous(int [] arr) {
        int count = 0;

       for (int i = 0 ; i < arr.length - 2; i++) {
           if (arr[i] - arr[i + 1] == -1 && arr[i + 1] - arr[i +2] == -1){
               count = count + 3;
               //System.out.print(arr[i] + "  "+ arr[i + 1 ]+" " + arr[i +2] + "  ");
           }
       }
       return count > 2;
    }

    /**
     * This method counts if there are more
     * than 3 numbers with the same ending e.g. 11,21,31,41
     *
     * @param arr
     *            the array with the numbers
     * @return
     *          true if there are more than 3 numbers with same ending, false otherwise
     */
    public static boolean isSameEnding(int [] arr){
        int [] count = new int[arr.length];
        int counter = 0;
        for (int i = 0; i < arr.length; i++){
                     count[i] = arr[i] % 10;
        }

        Arrays.sort(count);

        for (int i = 0; i < count.length -3; i ++) {
            if (count[i] == count[i + 1] && count[i+1] == count[i+2] && count[i+2] == count[i + 3]){
                counter = counter +4;
            }
        }
        return counter > 3;
    }

    /**
     * This method counts if there are more than 3 numbers
     * within the same ten e.g. 1,2,3,4
     *
     * @param arr
     *            the array with the numbers
     * @return
     *          true if there are more than 3 numbers in the same ten
     *          false otherwise
     */
    public static boolean isSameTen(int [] arr){
        int [] count = new int[arr.length];
        int counter = 0;
        for (int i = 0; i < arr.length; i++){
            count[i] = arr[i] / 10;
        }

        Arrays.sort(count);
        for (int i = 0; i < count.length -3; i ++) {
            if (count[i] == count[i + 1] && count[i+1] == count[i+2] && count[i+2] == count[i + 3]){
                counter = counter +4;
            }
        }

        return counter > 3;
    }
}