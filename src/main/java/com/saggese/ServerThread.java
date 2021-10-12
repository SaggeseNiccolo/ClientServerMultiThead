package com.saggese;

import java.io.*;
import java.net.*;

/**
 * 
 */
public class ServerThread extends Thread {
    ServerSocket server;
    Socket client;
    String receivedString;
    String modifiedString;
    BufferedReader inFromClient;
    DataOutputStream outToClient;

    public ServerThread(Socket socket, ServerSocket server) {
        this.client = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {
        inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outToClient = new DataOutputStream(client.getOutputStream());
        // ciclo infinito che termina con 'FINE'
        for (;;) {
            receivedString = inFromClient.readLine();
            if (receivedString == null || receivedString.equals("FINE") || receivedString.equals("STOP")) {
                outToClient.writeBytes(receivedString + " (=>server in chiusura...)" + '\n');
                System.out.println("Echo sul server in chiusura :" + receivedString);
                break;
            } else {
                outToClient.writeBytes(receivedString + " (ricevuta e ritrasmessa)" + '\n');
                System.out.println("Echo sul server : " + receivedString);
            }
        }
        outToClient.close();
        inFromClient.close();
        System.out.println("9 Chiusura socket" + client);
        client.close();
        if (receivedString.equals("STOP")) {
            server.close();
        }
    }
}

