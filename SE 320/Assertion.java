import java.util.Scanner;

public class Assertion {

	private static final int MIN = 0;
	private static final int MAX = 10; 
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please input an integer value between 0 and 10:\n");
		
		int inputNum = scanner.nextInt();
		
		assert (inputNum >= MIN && inputNum <= MAX) : "The entered number is out of range";
		
		System.out.println("You entered the number: " + inputNum);
		
		
	}
}
