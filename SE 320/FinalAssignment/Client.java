import java.io.*;
import java.net.*;
import java.util.Scanner;

// Credit to: Professor Akbas for some of the socket/server code

public class Client {

    private DataOutputStream outputToServer;
    private DataInputStream inputFromServer;

    public static void main(String[] args) {
        Client c = new Client();
        c.runClient();
    }

    public void runClient() {

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server
            inputFromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            outputToServer = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            System.err.println(ex);
        }

        try {

            Scanner scan = new Scanner(System.in);

            System.out.println("Please enter your weight in kilograms:");
            double weightInKg = scan.nextDouble();
            System.out.println("Please enter your height in meters:");
            double heightInM = scan.nextDouble();

            scan.close();

            // Send the weight and height to the server
            outputToServer.writeDouble(weightInKg);
            outputToServer.writeDouble(heightInM);
            outputToServer.flush();

            // Get bmi from the server
            double bmi = inputFromServer.readDouble();

            System.out.println("Weight in kg is: " + weightInKg + "\n");
            System.out.println("Height in m is: " + heightInM + "\n");
            System.out.println("BMI calculated from server is: " + bmi + '\n');

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
