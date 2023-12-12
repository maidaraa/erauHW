
import java.io.*;
import java.net.*;
import java.util.*;

// Credit to: Professor Akbas for some of the socket/server code

public class MultiThreadServer {
    public static void main(String[] args) {
        MultiThreadServer ms = new MultiThreadServer();
        ms.runServer();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Multi Thread Server started at " + new Date() + '\n');

            int clientNum = 1;

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client " + clientNum + " connected at " + new Date() + '\n');

                // Create a new thread for each client
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread clientThread = new Thread(clientHandler);

                clientNum++;
                clientThread.start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private class ClientHandler extends Thread {

        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Create data input and output streams for this client
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    double weightInKg = inputFromClient.readDouble();
                    double heightInM = inputFromClient.readDouble();
                    double bmi = weightInKg / (heightInM * heightInM);

                    outputToClient.writeDouble(bmi);

                    // checking if server received and calculated information correctly
                    System.out.println("Weight received from client: " + weightInKg + '\n');
                    System.out.println("Height received from client: " + heightInM + '\n');
                    System.out.println("BMI calculated: " + bmi + '\n');
                }
            } catch (IOException ex) {
                System.err.println(ex);

            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}