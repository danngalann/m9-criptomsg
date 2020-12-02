/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Daniel Galán
 */
public class MessageManager {
    public static Map<String, Object> sendMessage(String message, PublicKey remotePublicKey, PrivateKey privateKey){
        
        // Init final message
        Map<String, Object> finalMessage = new HashMap<>();
        
        // Make simetric key
        AES aes = new AES();
        SecretKey key = aes.getKey();
        
        // Make hash and concatenate with message
        String messageHash = SHA.makeHash(message);                
                
        try {          
            // Encrypt hash with your private key
            byte[] encryptedHash = RSA.encrypt(messageHash.getBytes(), privateKey);
            
            // Encript simetric key with remote public key
            byte[] encryptedSimetricKey = RSA.encrypt(key.getEncoded(), remotePublicKey);
            
            // Encrypt message
            byte[] encryptedMessage = aes.encrypt(message.getBytes());
            
            // Make final message with encripted message and encripted simetric key            
            finalMessage.put("hash", encryptedHash);
            finalMessage.put("message", encryptedMessage);
            finalMessage.put("key", encryptedSimetricKey);
        } catch (Exception ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return finalMessage;
    }
    
    public static String receiveMessage(Map<String, Object> encryptedData, PublicKey remotePublicKey, PrivateKey privateKey){
        
        String plainMessage = "";
        // Extract everything
        byte[] encryptedHash = (byte[]) encryptedData.get("hash");
        byte[] encriptedSimetricKey = (byte[]) encryptedData.get("key");
        byte[] encryptedMessage = (byte[]) encryptedData.get("message");
        
        try {
            // Decrypt hash with remote public key
            String hash = RSA.decrypt(encryptedHash, remotePublicKey);
            
            // Decrypt simetric key with private key
            byte[] encodedSimetricKey = RSA.decrypt(encriptedSimetricKey, privateKey);
            
            // Make AES key from raw bytes
            SecretKey simetricKey = new SecretKeySpec(encodedSimetricKey, 0, encodedSimetricKey.length, "AES");
            
            // Decrypt message with AES key
            String message = AES.decrypt(encryptedMessage, simetricKey);
            
            // Store decrypted message on return variable
            plainMessage = message;
        } catch (Exception ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return plainMessage;
    }
}
