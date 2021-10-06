package cinema;
import java.util.Scanner;

public class Cinema {
    static int tickets = 0;
    static float percentage = 0;
    static int income = 0;
    static int totalIncome = 0;

    public static void creatSeats(int row, int seatsRow, char[][] seats) {
        for (int i = 0; i <= row; i++) {
            for ( int j = 0; j <= seatsRow; j++) {
                if (i != 0 && j != 0)
                    seats[i][j] = 'S';
                else if (i == 0)
                    seats[i][j] = (char)( '0' + j);
                else
                    seats[i][j] = (char)( '0' + i);
            }
        }
        seats[0][0] = ' ';
    }

    public static void showSeats(int row, int seatsRow, char[][] seats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= row; i++) {
            for ( int j = 0; j <= seatsRow; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTickets(int row, int seatsRow, char[][] seats) {
        Scanner sc = new Scanner(System.in);
        boolean flag;
        do {
            System.out.println("Enter a row number:");
            int rowNum = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatsRowNum = sc.nextInt();

            if (rowNum < 1 || rowNum > row || seatsRowNum < 1 || seatsRowNum >seatsRow) {
                System.out.println("Wrong input!");
                flag = true;
            }else if (seats[rowNum][seatsRowNum] == 'B') {
                System.out.println("That ticket has already been purchased!");
                flag = true;
            }else {
                int price = 10;
                if ( (row * seatsRow >60) && (rowNum > row/2) ) {
                    price  = 8;
                }
                System.out.println("Ticket price: $"+price);

                seats[rowNum][seatsRowNum] = 'B';
                tickets++;
                percentage =(float) tickets / (row * seatsRow);
                income += price;
                flag = false;
                System.out.println();
            }
        }while(flag);
    }

    public static void Statistics() {
        System.out.printf("Number of purchased tickets: %d\n",tickets);
        System.out.printf("Percentage: %.2f%%\n",percentage * 100);
        System.out.printf("Current income: $%d\n",income);
        System.out.printf("Total income: $%d\n\n",totalIncome);

    }

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsRow = sc.nextInt();
        System.out.println();

        if ( row * seatsRow >60 ) {
            totalIncome = (row/2) * seatsRow * 10 + (row-row/2) * seatsRow * 8;
        }else {
            totalIncome = row * seatsRow * 10;
        }


        char[][] seats = new char[row+1][seatsRow+1];
        creatSeats(row, seatsRow, seats);

        while(true) {

            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            int option = sc.nextInt();
            System.out.println();
            switch(option) {
                case 0:
                    return;
                case 1:
                    showSeats(row, seatsRow, seats);
                    break;
                case 2:
                    buyTickets(row, seatsRow, seats);
                    break;
                case 3:
                    Statistics();
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }

    }

}