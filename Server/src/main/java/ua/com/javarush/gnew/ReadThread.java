package ua.com.javarush.gnew;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch(IOException e) {
            System.out.println("Error getting input stream: " + e.getMessage());
        }
    }

    public void run() {
        while(true) {
            try {
                String message = reader.readLine();
                if (message == null) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + message);
            } catch(IOException e) {
                System.out.println("Error reading from client: " + e.getMessage());
                break;
            }
        }
    }
}

