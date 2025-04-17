import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connection to server established to exit. Enter 'exit'.");

            while (true) {
                System.out.print("Enter a message: ");
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                out.println(message); // Send message to Server
                String response = in.readLine(); // Recieve Response from Server
                System.out.println("Server Response: " + response);
            }

            System.out.println("Exit the program.");
        } catch (IOException e) {
            System.out.println("Error in the client : " + e.getMessage());
        }
    }
}
