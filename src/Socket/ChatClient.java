/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Galán
 */
public class ChatClient {
    private String hostname;
    private int port;
    private String userName;
    private Socket socket;
    private ReadThread readThread;
    private WriteThread writeThread;
 
    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;        
    }
 
    public boolean execute() {
        try {
            
            this.socket = new Socket(hostname, port);
            
            System.out.println("Connected to the chat server");
            
            this.readThread = new ReadThread(socket, this);
            this.writeThread = new WriteThread(socket, this);
            
            this.readThread.start();
            this.writeThread.start();
            
            return true;
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
        
        return false;
 
    }
    
    public void disconnect(){
        try {
            readThread.exit();
            writeThread.exit();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
}
