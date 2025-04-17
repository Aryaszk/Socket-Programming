# Member : Aryas Zandkarimi & Matin Mohammadi

# program language : Java

# GitHub links :


# Java Socket Client-Server Communication

A simple Java implementation of a client-server application using sockets. The server listens for incoming client connections, receives messages, and echoes them back with a confirmation.

## Features
- **Server**: Listens on a specified port, accepts client connections, and echoes received messages.
- **Client**: Connects to the server, sends user-input messages, and displays server responses.
- **Real-time Communication**: Supports continuous message exchange until the client exits.

## Technologies Used
- **Language**: Java (JDK 8+)
- **Core Libraries**: `java.net` (Sockets), `java.io` (Streams)

## How It Works
1. The **Server** binds to port `1234` and waits for client connections.
2. The **Client** connects to `localhost:1234` and sends messages entered by the user.
3. The server echoes each message back to the client with a confirmation prefix.

## Setup & Execution

### Prerequisites
- Java JDK 8 or later installed.
- IDE (e.g., IntelliJ, Eclipse) or command-line tools.

### Steps
1. **Compile the Code**:
   ```bash
   javac Server.java
   javac Client.java


# Run the Server 

java Server

The server will start and display:
سرور فعال است و منتظر اتصال کلاینت‌ها روی پورت 1234 ...
(Server is active and waiting for clients on port 1234...)

# Run the Client 

java Client
The client will connect and prompt:
اتصال به سرور برقرار شد. برای خروج 'exit' را وارد کنید.
(Connected to server. Type 'exit' to quit.)

## Send Messages

Enter messages in the client terminal. The server will reply with:
سرور دریافت کرد: [your message]
(Server received: [your message])

Exit: Type exit in the client to terminate the session


Code Structure


Server.java:


Creates a ServerSocket to listen for connections.

Uses BufferedReader to read client messages and PrintWriter to send responses.

Handles one client at a time (single-threaded).



Client.java:

Connects to the server using Socket.

Reads user input via Scanner and sends it to the server.

Displays server responses in the console.



# End of File