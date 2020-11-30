/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


/**
 *
 * @author Daniel Galán
 */
public class WriteThread extends Thread {

    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
    private boolean exit;
 
    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        this.exit = false;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    @Override
    public void run() {
 
        String userName = "dan"; // TODO: Set this to a random UUID or something
        client.setUserName(userName);
        writer.println(userName);
 
        String text;
 
        do {
            text = "hello motherfucker"; // TODO: Get this from GUI
            writer.println(text);
 
        } while (!text.equals("bye") && !exit);
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
    
    public void exit(){
        exit = true;
    }
}
