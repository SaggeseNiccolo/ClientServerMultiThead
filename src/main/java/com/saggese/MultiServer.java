package com.saggese;

import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 */
public class MultiServer {

    // public static Vector ports = new Vector();

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            
            for (;;) {
                System.out.println("1 Server in attesa ... ");
                Socket socket = serverSocket.accept();
                // ports.add(socket.getPort());
                // for(int i = 0; i < ports.size(); i++){
                //     System.out.println(ports.get(i));
                // }
                System.out.println("3 Server socket " + socket);
                ServerThread serverThread = new ServerThread(socket, serverSocket);
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        MultiServer tcpServer = new MultiServer();
        tcpServer.start();
    }
}
