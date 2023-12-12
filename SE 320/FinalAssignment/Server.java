
import java.io.*;
import java.net.*;
import java.util.*;

// Credit to: Professor Akbas for some of the socket/server code

public class Server {
    public static void main(String[] args) {
        Server s = new Server();
        s.runServer();
    }

    public void runServer() {

        try {

            ServerSocket serverSocket = new ServerSocket(8000);

            System.out.println("Server started at " + new Date() + '\n');

            Socket socket = serverSocket.accept();

            // Create data input and output streams
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            while (true) {

                double weightInKg = inputFromClient.readDouble();
                double heightInM = inputFromClient.readDouble();
                double bmi = weightInKg / (heightInM * heightInM);

                outputToClient.writeDouble(bmi);

                // checking if server recieved and calculated information correctly
                System.out.println("Weight received from client: " + weightInKg + '\n');
                System.out.println("Height received from client: " + heightInM + '\n');
                System.out.println("BMI calculated: " + bmi + '\n');

            }
        } catch (IOException ex) {

            System.err.println(ex);
        }
    }
}