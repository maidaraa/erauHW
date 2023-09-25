// Mai Evans 9/25/2023
import java.util.Scanner;
import java.util.InputMismatchException;

public class CalcSum {

    private int x, y, sum;
    private boolean isValid = false;

    public static void main(String[] args) {
        CalcSum cs = new CalcSum();
        cs.runProgram();
    }

    private void runProgram() {

        Scanner scan = new Scanner(System.in);

        while (!isValid) {
            try {
                System.out.println("Please enter your first integer: ");
                x = scan.nextInt();
                System.out.println("Please enter your second integer: ");
                y = scan.nextInt();
                isValid = true;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, try again.");
                scan.nextLine();
            }
        }
        sum = x + y;
        System.out.println("The sum is: " + sum);
        scan.close();

    }
}
