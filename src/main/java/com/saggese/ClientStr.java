package com.saggese;

import java.io.*;
import java.net.*;

/**
 * 
 */
public class ClientStr {
    String serverName = "localhost";
    int serverPort = 6789;
    Socket mysocket;
    BufferedReader keyboard;
    String userString;
    String stringFromServer;
    DataOutputStream outToServer;
    BufferedReader inFromServer;

    public Socket connetti() {
        System.out.println("2 CLIENT partito in esecuzione...");
        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mysocket = new Socket(serverName, serverPort);
            outToServer = new DataOutputStream(mysocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return mysocket;
    }

    public void comunica() {
        for (;;)
        try {
            System.out.println("4... inserisci la stringa da trasmettere al server:");
            userString = keyboard.readLine();
            System.out.println("5 ... invio la stringa al server e attendo ...");
            outToServer.writeBytes(userString + '\n');
            stringFromServer = inFromServer.readLine();
            System.out.println("7 ... risposta dal server " + '\n' + stringFromServer);
            if (userString.equals("FINE")) {
                System.out.println("8 CLIENT: termina elaborazione e chiude la connessione");
                mysocket.close();
                break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        ClientStr client = new ClientStr();
        client.connetti();
        client.comunica();
    }
}
