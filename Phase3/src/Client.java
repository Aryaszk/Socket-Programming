import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to server. Type your messages:");
            System.out.println("Type '/exit' to quit the chat.");

            // 🔹 Thread برای دریافت پیام‌های زنده
            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("\n[New message] " + serverMessage);
                        System.out.print("> ");
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            readThread.start();

            // 🔹 Thread اصلی برای ورودی کاربر
            String userInput;
            while (true) {
                System.out.print("> ");
                userInput = scanner.nextLine();

                if ("/exit".equalsIgnoreCase(userInput.trim())) {
                    out.println("[Client disconnected]");
                    break;
                }

                out.println(userInput);
            }

            socket.close();
            System.out.println("You have left the chat.");

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}




