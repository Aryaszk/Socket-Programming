import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Multi-threaded server started on port " + port);

            while (true) {
                // When a client connects
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected from " + clientSocket.getInetAddress());

                // Create a new thread for each client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start(); // Thread execution
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}


