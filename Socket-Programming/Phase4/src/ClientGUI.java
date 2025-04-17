import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientGUI extends JFrame {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;

    private String username;

    public ClientGUI(String host, int port) {
        setTitle("Java Chat Client");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        username = JOptionPane.showInputDialog(this, "Enter your username:");
        if (username == null || username.trim().isEmpty()) {
            username = "Anonymous";
        }

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        connectToServer(host, port);

        setVisible(true);
    }

    private void connectToServer(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        messageArea.append(serverMessage + "\n");
                    }
                } catch (IOException e) {
                    messageArea.append("Disconnected from server.\n");
                }
            }).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to connect to server.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            out.println(username + ": " + message);
            if ("/exit".equalsIgnoreCase(message)) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI("localhost", 1234));
    }
}



