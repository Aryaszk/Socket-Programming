# Member : Aryas Zandkarimi & Matin Mohammadi

# program language : Java

# GitHub links :


# Java Socket Chat Application

A simple multi-client chat application built in Java using sockets, supporting public and private messaging, username display, and a graphical client.

--------------------------------------------------------------------------

## Features

--  **Server** that handles multiple clients concurrently via threads.
--  **Public messages** broadcasted to all connected clients.
--  **Private messaging** using a simple command:


-- **Username support** in the graphical client.
-- **Timestamp and username display** with each message (in the GUI client).
-- **Graphical client (Swing-based)** and **Console client** available.
-- **Automatic removal of disconnected clients**.
-- **Error handling and connection stability improvements**.

---------------------------------------------------------------------------

## Project Structure



## How to Run

-- Compile the server and client classes

Open a terminal inside the folder and run:

## bash
javac Server.java ClientHandler.java ClientGUI.java


--------------------------------------------------------------------------

## Commands

-- Public Message :
Just type your message and hit Enter — everyone will receive it.

-- Private Message :
To send a private message: >pm <clientIp> <your message>

-- Example : >pm /127.0.0.1 Hello, this is private!


--------------------------------------------------------------------------

## Exit : /exit
   



# End Of File.







