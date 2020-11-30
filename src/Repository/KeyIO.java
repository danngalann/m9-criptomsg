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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Galán
 */
public class KeyIO {
    
    public static void serializePublic(PublicKey key){
        try {
         FileOutputStream fileOut = new FileOutputStream("files/own/public.key");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(key);
         out.close();
         fileOut.close();
      } catch (IOException i) {
         i.printStackTrace();
      }
    }
    
    public static void serializePublic(PublicKey key, String path){
        try {
         FileOutputStream fileOut = new FileOutputStream(path);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(key);
         out.close();
         fileOut.close();
      } catch (IOException i) {
         i.printStackTrace();
      }
    }
    
    public static void serializePrivate(PrivateKey key){
        try {
         FileOutputStream fileOut = new FileOutputStream("files/own/private.key");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(key);
         out.close();
         fileOut.close();
      } catch (IOException i) {
         i.printStackTrace();
      }
    }
    
    public static PublicKey loadPublic(){
        
        PublicKey publicKey = null;
        
        try {
         FileInputStream fileIn = new FileInputStream("files/public.key");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         publicKey = (PublicKey) in.readObject();
         in.close();
         fileIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(KeyIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publicKey;
    }
    
    public static PublicKey loadPublic(String path){
        PublicKey publicKey = null;
        
        try {
         FileInputStream fileIn = new FileInputStream(path);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         publicKey = (PublicKey) in.readObject();
         in.close();
         fileIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(KeyIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publicKey;
    }
    
}
