package gr.aueb.cf.Projects;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program books seats in a theater(array),
 * it gets the choice from the user and books the seat if available
 * also user can cancel a reservation
 */
public class BookingTheaterApp {
    final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean[][] arr = new boolean[30][12];
        int usersChoice = 0;

        printTheaterSeats(arr);
        while (usersChoice != 3){
            usersChoice = getChoice();
            userAccessToService(usersChoice,arr);
        }
        System.out.println("you chose exit");
    }

    /**
     * This method books a seat for the user if non-booked
     * @param row
     *              the row user has chosen
     * @param column
     *              the column user has chosen
     * @param arr
     *          the array that contains the seats
     *                the labels of the columns
     */
    public static void book(int row, int column, boolean[][]arr){
        if (arr[row][column]){
            printTheaterSeats(arr);
            System.out.println("seat: "+ (char)(column + 65) + (row + 1)  + "  is already booked");
        }else {
            arr[row][column] = true;
            printTheaterSeats(arr);
            System.out.println("You booked seat: "  + (char)(column + 65) + "-" +  (row + 1) );
        }
    }

    /**
     * This method cancels a booked seat
     * checks if it is already booked and cancels the reservation
     * or gives message to user that seat is non booked
     * @param row
     *              the row user has chosen
     * @param column
     *              the column user has chosen
     * @param arr
     *          the array that contains the seats
     */
    public static void cancel(int row, int column,boolean [][]arr){
        if (!arr[row][column]){
            printTheaterSeats(arr);
            System.out.println("Seat  "+  (char)(column + 65) + (row + 1)  + "  is not booked so you can't cancel");
        }else {
            arr[row][column] = false;
            printTheaterSeats(arr);
            System.out.println(" Successful cancellation of seat: " +  (char)(column + 65) + (row + 1));
        }
    }

    /**
     * Prints the seats of the theater
     * @param arr
     *              the array that contains the seats, if true they
     *              are booked, if false they are non-booked
     */
    public static void printTheaterSeats(boolean[][] arr){

        for (int i = 0 ; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j ++){
                System.out.printf("%3s",(char)(j + 65));
                System.out.printf("%2d",i + 1);
                System.out.print (arr[i][j] ?" booked-seat  " : " NON-booked   ");
            }
            System.out.println();
        }
    }

    /**
     * This method displays the options user has
     * and gets the choice from the user 1 for booking
     * 2 for cancellation 3 for exit
     * @return
     *          user's choice
     */
    public static int getChoice(){
        int choice = 0;
        System.out.println("Please choose 1 to book a seat, 2 to cancel a booked seat or 3 to exit");
        while (true){
            try {
                choice = in.nextInt();
                if (choice >=1 && choice <= 3){
                    break;
                }else System.out.println("Please choose 1 to book a seat, 2 to cancel a booked seat or 3 to exit");
            }catch (InputMismatchException e){
                in.nextLine();
                System.out.println("Please choose 1 to book a seat, 2 to cancel a booked seat or 3 to exit");
            }
        }
        return choice;
    }

    /**
     *  This method evaluates the user's choice
     *  and then proceeds to the next service for the user
     *  if choice  is 1 then booking
     *  if choice  is 2 then cancellation
     *   @param choice
     *                  user's choice
     * @param arr
     *              the array that contains the seats
     *              the labels of the columns
     */
    public static void userAccessToService(int choice, boolean [][]arr){
        int j = 0;
        int i = 0;

        if (choice == 1) {
            System.out.println("You chose to book a seat");
            j = getColumns();
            i = getRow();
            book(i,j,arr);
        } else if (choice == 2){
            System.out.println("you chose to cancel a booked seat");
            j = getColumns();
            i = getRow();
            cancel(i,j,arr);
        }
    }

    /**
     * This method gets user's choice for columns
     * checks if the choice is valid
     * @return
     *          the column user has chosen
     */
    public static int getColumns(){
        String userColumns;
        int j = 0;
        boolean isABC = false;

        System.out.println("choose between A - L for column");
        while(!isABC){
            userColumns = in.next().toUpperCase();
            if (userColumns.length() > 1) {
                System.out.println("Please enter only one letter between A - L for column.");
                continue;
            }
            char letter = userColumns.charAt(0);
            if ((int) letter >= 65 && (int) letter <= 76) {
                isABC = true;
                j = (int) letter - 65;
            } else {
                System.out.println("Please choose between A - L for column");
            }
        }return j;
    }

    /**
     * This method gets user's choice for row
     * checks if the choice is valid
     * @return
     *         the row user has chosen
     */
    public static int getRow(){
        int row = 0;

        System.out.println(" please choose row 1-30");
        while (true){
            try {
                row = in.nextInt();
                if (row >= 1 && row <= 30){
                    break;
                }else {
                    System.out.println("please insert between 1 and 30.");
                }
            }catch (InputMismatchException e){
                System.out.println("Please give an integer between 1 and 30 for rows");
                in.nextLine();
            }
        }
        row = row - 1;
        return  row;
    }
}