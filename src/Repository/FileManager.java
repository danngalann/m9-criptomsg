/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class FileManager {
    
    /**
     * Read a message from a binary file
     * @param path
     * @return 
     */
    public static Map<String, Object> readMessage(String path){
        Map<String, Object> message = null;
        
        try {
         FileInputStream fileIn = new FileInputStream(path);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         message = (Map<String, Object>) in.readObject();
         in.close();
         fileIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(KeyIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    /**
     * Save a message in a binary file
     * @param message
     * @param path 
     */
    public static void saveMessage(Map<String, Object> message, String path){
        try {
         FileOutputStream fileOut = new FileOutputStream(path);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(message);
         out.close();
         fileOut.close();
      } catch (IOException i) {
         i.printStackTrace();
      }
    }
    
}
