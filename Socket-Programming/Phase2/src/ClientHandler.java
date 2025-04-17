import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String clientMessage;
            System.out.println("Client " + clientSocket.getInetAddress() + " connected.");

            // Receive messages from the client and send responses
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("From " + clientSocket.getInetAddress() + ": " + clientMessage);
                out.println("Server received: " + clientMessage);

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    break;
                }
            }

            // When the client disconnects
            System.out.println("Client " + clientSocket.getInetAddress() + " disconnected.");
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("Error with client: " + e.getMessage());
        }
    }
}


