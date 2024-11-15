package ua.com.javarush.gnew;

import java.io.*;
import java.net.*;
import java.util.Scanner;


class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private Scanner scanner;

    public WriteThread(Socket socket) {
        this.socket = socket;
        scanner = new Scanner(System.in);

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true); // Auto-flush enabled
        } catch(IOException e) {
            System.out.println("Error getting output stream: " + e.getMessage());
        }
    }

    public void run() {
        while(true) {
            String message = scanner.nextLine();
            writer.println(message);

            if (message.equalsIgnoreCase("bye")) {
                try {
                    socket.close();
                    break;
                } catch(IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}