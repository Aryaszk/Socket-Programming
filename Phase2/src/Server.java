import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Multi-threaded server started on port " + port);

            while (true) {
                // وقتی یک کلاینت وصل میشه
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected from " + clientSocket.getInetAddress());

                // ساخت یک Thread جدید برای هر کلاینت
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start(); // اجرای Thread
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}


