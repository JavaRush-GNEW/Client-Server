package ua.com.javarush.gnew;

import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args) {
        int port = 12345; // Specify your desired port number

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for a client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Start threads for reading and writing
            ReadThread readThread = new ReadThread(socket);

            readThread.start();

            // Wait for both threads to finish
            readThread.join();

            socket.close();
            serverSocket.close();
            System.out.println("Server terminated.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}