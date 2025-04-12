import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("سرور فعال است و منتظر اتصال کلاینت‌ها روی پورت " + port + " ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("کلاینت متصل شد: " + clientSocket.getInetAddress());

                // پردازش پیام‌ها
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String messageFromClient;
                while ((messageFromClient = in.readLine()) != null) {
                    System.out.println("پیام دریافتی از کلاینت: " + messageFromClient);
                    out.println("سرور دریافت کرد: " + messageFromClient); // ارسال پاسخ به کلاینت
                }

                clientSocket.close();
                System.out.println("اتصال کلاینت بسته شد.");
            }
        } catch (IOException e) {
            System.out.println("خطای سرور: " + e.getMessage());
        }
    }
}


