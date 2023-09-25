// Mai Evans 9/25/2023
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayIndexOutOfBoundsException;

public class ArrayIndex {
    
    public static void main(String[] args){
      
        Random rand = new Random();
        int[] arr = new int[99];
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt();
        }
      
        try{
            System.out.println("Please enter the index of the array you want to view: ");
            int index = scan.nextInt();
            System.out.println("" + arr[index]);
          
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Out of bounds");
        }
        scan.close();
    }
}
