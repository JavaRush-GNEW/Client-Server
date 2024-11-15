package ua.com.javarush.gnew;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost"; // Replace with server's IP if not running locally
        int port = 12345; // Ensure this matches the server's port

        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the server.");

            // Start threads for reading and writing
            ReadThread readThread = new ReadThread(socket);
            WriteThread writeThread = new WriteThread(socket);

            readThread.start();
            writeThread.start();

            // Wait for both threads to finish
            readThread.join();
            writeThread.join();

            socket.close();
            System.out.println("Client terminated.");

        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}