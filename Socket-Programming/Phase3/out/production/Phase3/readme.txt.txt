# Member : Aryas Zandkarimi

# program language : Java

# GitHub link :

# Multi-Threaded Chat Server

A Java-based chat application featuring a server that handles multiple concurrent client connections with real-time message broadcasting.

## Features ✨
- **Multi-client support**: Handles unlimited concurrent connections
- **Real-time broadcasting**: Messages instantly delivered to all connected clients
- **Thread-safe operations**: Synchronized client management
- **Graceful disconnect**: Clean connection termination with `/exit` command
- **Dedicated I/O threads**: Non-blocking message reading/writing

# Run The Server


java Server

Output: Multi-threaded server started on port 1234


# Run the Client

javac Client.java
java Client

Output: > Hello everyone!
[New message] Hello everyone!  # Broadcast to all clients
> /exit


# Server Components


Server.java :

Maintains thread-safe client set (synchronized)

Implements broadcast functionality

Port: 1234 (configurable)



ClientHandler.java :

Dedicated thread per client

Handles message receiving/broadcasting

Implements connection cleanup



Client.java :

Dual-thread design:

Main thread: User input

Background thread: Message reception

Supports command /exit


# Message Flow 📨


Client A sends message

Server receives → broadcasts to Clients B...N

All clients display message with [New message] prefix


## End of File


