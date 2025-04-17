import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String clientMessage;
            System.out.println("Client " + clientSocket.getInetAddress() + " connected.");

            while ((clientMessage = in.readLine()) != null) {
                System.out.println("From " + clientSocket.getInetAddress() + ": " + clientMessage);

                // Send message to all clients
                Server.broadcastMessage(clientMessage, this);

                // If the client sends '/exit', the connection is closed.
                if ("/exit".equalsIgnoreCase(clientMessage.trim())) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error with client " + clientSocket.getInetAddress() + ": " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
            synchronized (Server.clientHandlers) {
                Server.clientHandlers.remove(this);
            }
            System.out.println("Client " + clientSocket.getInetAddress() + " disconnected.");
        }
    }

    // Method for sending a message to the client
    public void sendMessage(String message) {
        out.println(message);
    }
}


