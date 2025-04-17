# Member : Aryas Zandkarimi & Matin Mohammadi

# program language : Java

# GitHub links :


# Multi-Threaded Socket Server and Client

A Java implementation of a multi-threaded server that handles multiple client connections simultaneously, with a simple client application for message exchange.

## Features
- **Multi-threaded Server**: Handles each client connection in a separate thread
- **Persistent Connections**: Maintains connections until client disconnects
- **Bidirectional Communication**: Clients can send messages and receive server responses
- **Graceful Termination**: Supports "exit" command to close connections cleanly

## Technologies
- **Language**: Java (JDK 8+)
- **Core Libraries**:
  - `java.net` (ServerSocket, Socket)
  - `java.io` (Streams, Readers/Writers)
- **Concurrency**: Native Java Threads


## Setup & Usage

### Prerequisites
- Java JDK 8 or later
- Terminal or IDE to run Java applications

### Running the Server
1. Compile:
   ```bash
   javac Server.java ClientHandler.java

## Run java Server & Clients

Server starts on port 1234

Output: "Multi-threaded server started on port 1234"

javac Client.java
java Client

Client connects to localhost:1234

Interaction:

Type messages to send to server

Type "exit" to disconnect.

## Code Structure


Server Components

Server.java :

Creates ServerSocket on port 1234

Accepts new connections in infinite loop

Spawns new ClientHandler thread for each client


ClientHandler.java (extends Thread) :


Handles individual client connections

Processes incoming messages

Sends acknowledgments back to client

Manages connection lifecycle


Client.java :

Connects to server

Provides user input interface

Displays server responses.

## End of File





