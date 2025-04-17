import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    public static List<ClientHandler> clientHandlers = new ArrayList<>();
    public static Map<String, ClientHandler> clientMap = new HashMap<>();

    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                synchronized (clientHandlers) {
                    clientHandlers.add(handler);
                    clientMap.put(clientSocket.getInetAddress().toString(), handler);
                }
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Send to all
    public static void broadcastMessage(String message, ClientHandler sender) {
        synchronized (clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                if (handler != sender) {
                    handler.sendMessage(message);
                }
            }
        }
    }

    // Send private message
    public static void sendPrivateMessage(String clientIp, String message, ClientHandler sender) {
        ClientHandler recipient = clientMap.get(clientIp);
        if (recipient != null) {
            recipient.sendMessage("[Private from " + sender.getClientIp() + "]: " + message);
            sender.sendMessage("[Private to " + clientIp + "]: " + message);
        } else {
            sender.sendMessage("Client with IP " + clientIp + " not found.");
        }
    }
}


