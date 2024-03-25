/* Author: Mai Evans
 * ID: 2578013
 * Project: PA1 Binary Search HW
 * 
 * Program description: This program shall execute the binary search algorithm on a key and sorted array 
 * that are provided at the command line and shall output an integer representing the index location where 
 * the search key was found or the integer “-1” if not found.
 * 
 */



 public class BinarySearch {
    
    public static void main(String[] args){
        // create an instance of the class and run the program by passing the arguments to the runProgram method
        BinarySearch bs = new BinarySearch();
        bs.runProgram(args);
    
    
    }
    // method to run the main program
    public void runProgram(String[] args){
    
        // checks if there are at least 2 arguments
        if (args.length < 2) {
             System.out.println("Usage: java BinarySearch <key> <val1> <val2>..");
             return; // exit if not
        }
    
        // parse the first argument as an integer 
        int key = Integer.parseInt(args[0]);
        // create an int array to store the remaining argument values 
        int[] arr = new int[args.length -1];
    
        // parse the remaining arguments as integers + store them in the array
        for (int i = 1; i < args.length; i++){
            arr[i - 1] = Integer.parseInt(args[i]);
    
        }
        // run the binary search alg and store the result
        int index = searchAlg(key, arr, 0, arr.length -1);
        // print the index where the key is located
        System.out.println("Key is located at index: " + index);
    
    
    }
    
    // algorithm which finds where the key value in an integer array is stored
    public int searchAlg(int key, int[] arr, int lo, int hi){
    
        if (lo > hi){
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (key < arr[mid]) {
            return searchAlg(key, arr, lo, mid -1);
        }
        else if (key > arr[mid]){
            return searchAlg(key, arr, mid +1, hi);
        }
        else {
            return mid; 
        }
    }
    
    }
    