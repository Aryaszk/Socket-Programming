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

            System.out.println("اتصال به سرور برقرار شد. برای خروج 'exit' را وارد کنید.");

            while (true) {
                System.out.print("پیامی وارد کنید: ");
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                out.println(message); // ارسال پیام به سرور
                String response = in.readLine(); // دریافت پاسخ از سرور
                System.out.println("پاسخ سرور: " + response);
            }

            System.out.println("خروج از برنامه.");
        } catch (IOException e) {
            System.out.println("خطا در کلاینت: " + e.getMessage());
        }
    }
}
