package gr.aueb.cf.Projects;

/**
 *This program finds the largest consecutive sum in an array of integers,
 *  the position where the max-sum starts and the position it ends.
 */
public class MaxSumSubArrayApp {

    public static void main(String[] args) {
        //Initialing
        int [] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int local_max = 0;
        int local_position = 0;
        int global_max = 0;
        int highIndex = 0;
        int lowIndex = 0;
        int count = 0;
        int s = 0;


        //Checking for the max-sum
        for (int i = 0; i < arr.length; i ++){
                local_max += arr[i];

                if (local_max > global_max){
                    global_max = local_max;
                    local_position = i ;
                    lowIndex = s;
                }
                if (local_max < 0){
                    local_max = 0;
                        s = i + 1;
                }
                highIndex = local_position;
        }
        System.out.println(  lowIndex +"   "+  highIndex +"  " +  global_max);

    }
}
