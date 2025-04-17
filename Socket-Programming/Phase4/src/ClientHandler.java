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
            System.out.println("Client " + getClientIp() + " connected.");

            while ((clientMessage = in.readLine()) != null) {
                System.out.println("From " + getClientIp() + ": " + clientMessage);

                // If the message is private
                if (clientMessage.startsWith(">pm")) {
                    String[] parts = clientMessage.split(" ", 3);
                    if (parts.length >= 3) {
                        String targetIp = parts[1];
                        String message = parts[2];
                        Server.sendPrivateMessage(targetIp, message, this);
                    } else {
                        sendMessage("Invalid private message format. Use: >pm <clientIp> <message>");
                    }
                }
                // If the message was exit
                else if ("exit".equalsIgnoreCase(clientMessage)) {
                    break;
                }
                // General message
                else {
                    Server.broadcastMessage(getClientIp() + ": " + clientMessage, this);
                }
            }
        } catch (IOException e) {
            System.out.println("Error with client " + getClientIp() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing client socket: " + e.getMessage());
        }
        synchronized (Server.clientHandlers) {
            Server.clientHandlers.remove(this);
            Server.clientMap.remove(getClientIp());
        }
        System.out.println("Client " + getClientIp() + " disconnected.");
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getClientIp() {
        return clientSocket.getInetAddress().toString();
    }
}

