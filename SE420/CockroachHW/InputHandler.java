import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InputHandler {
    
     public void input(Scanner in){
        
        System.out.println("Input a lenghth and height greater than or equal to 2 x 2");
       
        int m = in.nextInt();
        int n = in.nextInt();

        //System.out.println(m + "and" + n);

        System.out.println("Input a location for the aspirin and glass of water");
        int a = in.nextInt();
        int w = in.nextInt();

        //System.out.println(a + "and" + w);
        in.nextLine(); 
        System.out.println("Enter the name of the output file (e.g., output.txt): \n");
        String fileName = in.nextLine();
        //String g = in.nextLine();

        // Get the directory path
        System.out.println("Enter the location to save the file (e.g., C:/Users/YourName/Documents): ");
        String directoryPath = in.nextLine();

        // Create the file path
        String filePath = directoryPath + File.separator + fileName;

        // Write to the file
        try {
            File file = new File(filePath);

            // Check if the file already exists
            if (file.exists()) {
                System.out.println("File already exists. Overwriting...");
            } else {
                // Create the file if it doesn't exist
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("Failed to create the file.");
                }
            }

            // Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(file);
            writer.write("This is a test output.\nFile created successfully!");
            writer.close();

            System.out.println("File saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();  // Provides more detailed information for debugging
        }
    }


}
