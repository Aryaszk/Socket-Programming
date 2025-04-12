import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 1234;
    public static final Set<ClientHandler> clientHandlers = new HashSet<>(); // لیست کلاینت‌های متصل

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Multi-threaded server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected from " + clientSocket.getInetAddress());

                // ساخت یک ClientHandler برای هر کلاینت
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                synchronized (clientHandlers) {
                    clientHandlers.add(clientHandler); // اضافه کردن کلاینت به لیست
                }
                clientHandler.start(); // اجرای Thread برای هر کلاینت
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }

    // متدی برای ارسال پیام به همه کلاینت‌ها
    public static void broadcastMessage(String message, ClientHandler sender) {
        synchronized (clientHandlers) {
            for (ClientHandler clientHandler : clientHandlers) {
                // ارسال پیام به همه غیر از فرستنده
                if (clientHandler != sender) {
                    clientHandler.sendMessage(message);
                }
            }
        }
    }
}

