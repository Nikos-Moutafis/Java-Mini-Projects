package gr.aueb.cf.Projects;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program implements a tic-tac-toe game
 * It reads from users(players) their choice
 * and updates an array with values of 1 for player1
 * and 0 of player2
 */
public class TicTacToeApp {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        //999 as default value of the array
        int[][] arr ={{999,999,999},
                     {999, 999, 999},
                     {999, 999, 999}};
        System.out.println("Welcome to tic-tac-toe game!! ");
        printBoard(arr);

        do {
            getPlayer1Choice(arr);
            printBoard(arr);
            if (endGame(arr) || isADraw(arr))break;
            getPlayer2Choice(arr);
            printBoard(arr);
        }while (!endGame(arr) && !isADraw(arr));
    }

    /**
     * This method gets player's 1 choice
     * and checks if the spot in the array is available
     * if it's not available gets an updated choice
     * @param arr
     *             the array with the values
     */
    public static void getPlayer1Choice  (int [][]arr) {
        int i = 0;
        int j = 0;
        int player1 = 1;

        do {
            i = getRow(player1);
            j = getColumn(player1);
        }while (isTaken(arr,i,j));
        arr[i][j] = 1;
    }

    /**
     * This method gets player's 2 choice
     * and checks if the spot in the array is available
     * if it's not available gets an updated choice
     * @param arr
     *             the array with the values
     */
    public static void  getPlayer2Choice(int [][]arr){
        int i = 0;
        int j = 0;
        int player2 = 2;

        do {
            i = getRow(player2);
            j = getColumn(player2);
        }while (isTaken(arr,i,j));

        arr[i][j] = 0;
    }

    /**
     * This method checks if the spot
     * the user inserted is available(in this case default value is 999)
     *
     * @param arr
     *             the array with values
     * @param i
     *          the row of the array user chose
     * @param j
     *          the column of the array user chose
     * @return
     *          true if the spot is available, false otherwise
     */
    public static boolean isTaken(int [][]arr, int i, int j) {

            if (arr[i][j] != 999) {
                printBoard(arr);
                System.out.println("this spot is already taken from other player");
                return true;
            } else return false;
        }

    /**
     * This method reads from standard input
     * the row user has chosen,also
     * checks if the choice is valid
     * @param player
     *              the user that made the choice
     * @return
     *         the row the user chose
     */
    public static int getRow(int player){
        int playerChoiceRow = 0;
        int i = 0;

        if (player == 1){
            System.out.println("Player one please choose row(1-3)  to enter X  ");
        } else if (player == 2) {
            System.out.println("Player two please choose row(1-3)  to enter O");
        }
        while (true){
                try {
                    playerChoiceRow = in.nextInt();
                    if (playerChoiceRow >= 1 && playerChoiceRow <= 3){
                        break;
                    }else {
                        System.out.println("please insert between 1 and 3.");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please give an integer between 1 and 3");
                    in.nextLine();
                }
            }
            i = playerChoiceRow - 1;
            return i;
        }

     /** This method reads from standard input
     * the column user has chosen,also
     * checks if the choice is valid
     * @param player
     *              the user that made the choice
     * @return
     *         the column the user chose
     */

    public static int getColumn(int player) {
            boolean isABC = false;
            String playerChoiceColumn ;
            int j = 0;

            if (player == 1){
                System.out.println("Player one please choose column(A-C)  to enter X  ");
            } else if (player == 2) {
                System.out.println("Player two please choose column(A-C)  to enter 0");
            }
            while(!isABC){
                playerChoiceColumn = in.next();
                if (playerChoiceColumn.compareToIgnoreCase("A") == 0){
                    isABC =true;
                } else if (playerChoiceColumn.compareToIgnoreCase("B") == 0) {
                    isABC =true;
                    j = 1;
                } else if (playerChoiceColumn.compareToIgnoreCase("C") == 0) {
                    isABC = true;
                    j = 2;
                }
                System.out.println("Please choose between A - C");
            }
            return j;
    }

    /**
     * This method checks if the game ended
     * @param arr
     *              the array to be checked
     * @return
     *          true if the game is over, false otherwise
     */
    public static boolean endGame (int [][]arr){
        boolean endGame = false;
        int []vSum = new int[3];
        int []hSum = new int[3];


        //Check diagonals and if a user won
        if ((arr[0][0] + arr[1][1] + arr[2][2]) == 3 || (arr[0][2] + arr[1][1] + arr[2][0]) == 3){
            System.out.println("player 1 wins");
            endGame = true;
        } else if ((arr[0][0] + arr[1][1] + arr[2][2]) == 0 || (arr[0][2] + arr[1][1] + arr[2][0]) == 0) {
            System.out.println("player 2 wins");
            endGame = true;
        }

        //Calculates the sum of rows and columns
        for (int i = 0; i < arr.length; i++){
            for (int j = 0 ; j < arr[i].length; j++){
                hSum[i] += arr[i][j];
            }
            vSum[0] += arr[i][0];
            vSum[1] += arr[i][1];
            vSum[2] += arr[i][2];
        }

        //Checks if a player won by doing tic-tac-toe in
        // one of the columns
        for (int j : vSum) {
            if (j == 3) {
                System.out.println("player 1 wins!!");
                System.out.println("Thanks for playing!!");
                endGame = true;
            } else if (j == 0) {
                System.out.println("player 2 wins!!");
                System.out.println("Thanks for playing!!");
                endGame = true;
            }

        }

        //Checks if a player won by doing tic-tac-toe in
        // one of the rows
        for (int i : hSum){
            if (i == 3){
                System.out.println("player 1 wins");
                endGame = true;
            } else if (i == 0) {
                System.out.println("player 2 wins");
                endGame = true;
            }
        }
        return endGame;
    }

    /**
     * This method checks if the game is a draw
     * checks if the array contains at least one
     * default value(999) and stops
     * the game if all spots are taken
     * @param arr
     *            the array to be checked
     * @return
     *          true if the game is a draw, false otherwise
     */
    public static  boolean isADraw(int [][] arr){
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (anInt == 999) {
                    return false;
                }
            }
        }
        System.out.println("the game is a draw");
        return true;
    }

    /**
     * Prints the array
     *
     * @param arr the array
     */
    public static void  printBoard(int [][]arr) {
        char[] columns = {'A', 'B', 'C'};
        int line = 1;

        for (char column : columns) {
            System.out.print("   " + column);
        }

        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.print(line);
            line++;
            for (int j = 0; j < 3; j++) {
                switch (arr[i][j]) {
                    case 999:
                        System.out.print("  - ");
                        break;
                    case 1:
                        System.out.print("  X ");
                        break;
                    case 0:
                        System.out.print("  O ");
                        break;
                }
            }
            System.out.println();
        }
    }
}

