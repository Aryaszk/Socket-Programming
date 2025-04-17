import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("The server is active and waiting for clients to connect on the port. " + port + " ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Messages processing
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String messageFromClient;
                while ((messageFromClient = in.readLine()) != null) {
                    System.out.println("Message received from the client: " + messageFromClient);
                    out.println("Server received: " + messageFromClient); // Send response to Client
                }

                clientSocket.close();
                System.out.println("Client connection closed..");
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}


